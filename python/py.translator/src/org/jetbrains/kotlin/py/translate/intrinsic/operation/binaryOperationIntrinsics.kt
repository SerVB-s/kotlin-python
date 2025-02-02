/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.py.translate.intrinsic.operation

import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.js.backend.ast.JsBinaryOperation
import org.jetbrains.kotlin.js.backend.ast.JsBinaryOperator
import org.jetbrains.kotlin.js.backend.ast.JsExpression
import org.jetbrains.kotlin.lexer.KtToken
import org.jetbrains.kotlin.psi.KtBinaryExpression
import org.jetbrains.kotlin.py.translate.context.TranslationContext
import org.jetbrains.kotlin.py.translate.operation.OperatorTable
import org.jetbrains.kotlin.py.translate.utils.BindingUtils.getCallableDescriptorForOperationExpression
import org.jetbrains.kotlin.py.translate.utils.PsiUtils.getOperationToken
import org.jetbrains.kotlin.py.translate.utils.TranslationUtils
import org.jetbrains.kotlin.py.translate.utils.getPrecisePrimitiveType
import org.jetbrains.kotlin.py.translate.utils.getPrimitiveNumericComparisonInfo
import org.jetbrains.kotlin.types.KotlinType

class BinaryOperationIntrinsics {

    private data class IntrinsicKey(
        val token: KtToken,
        val function: FunctionDescriptor,
        val leftType: KotlinType?,
        val rightType: KotlinType?
    )

    private val intrinsicCache = mutableMapOf<IntrinsicKey, BinaryOperationIntrinsic?>()

    fun getIntrinsic(expression: KtBinaryExpression, context: TranslationContext): BinaryOperationIntrinsic? {
        val descriptor =
            getCallableDescriptorForOperationExpression(context.bindingContext(), expression) as? FunctionDescriptor ?: return null

        val (leftType, rightType) = binaryOperationTypes(expression, context)

        val token = getOperationToken(expression)

        return computeAndCache(IntrinsicKey(token, descriptor, leftType, rightType))
    }

    private val factories = listOf(CompareToBOIF, EqualsBOIF)

    private fun computeAndCache(key: IntrinsicKey): BinaryOperationIntrinsic? {
        if (key in intrinsicCache) return intrinsicCache[key]

        val result = factories.firstNotNullOfOrNull { factory ->
            if (factory.getSupportTokens().contains(key.token)) {
                factory.getIntrinsic(key.function, key.leftType, key.rightType)
            } else null
        }

        intrinsicCache[key] = result

        return result
    }
}

// Takes into account smart-casts (needed for IEEE 754 comparisons)
fun binaryOperationTypes(expression: KtBinaryExpression, context: TranslationContext): Pair<KotlinType?, KotlinType?> {
    val info = context.getPrimitiveNumericComparisonInfo(expression)
    if (info != null) {
        return info.leftType to info.rightType
    }
    return expression.left?.let { context.getPrecisePrimitiveType(it) } to expression.right?.let { context.getPrecisePrimitiveType(it) }
}

interface BinaryOperationIntrinsicFactory {

    fun getSupportTokens(): Set<KtToken>

    fun getIntrinsic(descriptor: FunctionDescriptor, leftType: KotlinType?, rightType: KotlinType?): BinaryOperationIntrinsic?
}

typealias OperatorSelector = (KtBinaryExpression) -> JsBinaryOperator

val defaultOperatorSelector: OperatorSelector = { OperatorTable.getBinaryOperator(getOperationToken(it)) }

// toLeft(L, R) OP toRight(L, R)
fun complexBinaryIntrinsic(
    toLeft: (JsExpression, JsExpression, TranslationContext) -> JsExpression,
    toRight: (JsExpression, JsExpression, TranslationContext) -> JsExpression,
    operator: (KtBinaryExpression) -> JsBinaryOperator = defaultOperatorSelector
) = BinaryOperationIntrinsic { expression, left, right, context ->
    JsBinaryOperation(operator(expression), toLeft(left, right, context), toRight(left, right, context))
}

// toLeft(L, C) OP toRight(R, C)
fun binaryIntrinsic(
    toLeft: (JsExpression, TranslationContext) -> JsExpression = { l, _ -> l },
    toRight: (JsExpression, TranslationContext) -> JsExpression = { r, _ -> r },
    operator: OperatorSelector = defaultOperatorSelector
) = complexBinaryIntrinsic({ l, _, c -> toLeft(l, c) }, { _, r, c -> toRight(r, c) }, operator)


fun coerceTo(type: KotlinType): (JsExpression, TranslationContext) -> JsExpression =
    { e, c ->
        TranslationUtils.coerce(c, e, type)
    }