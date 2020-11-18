/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.js.transformers.irToPy

import generated.Python.*
import org.jetbrains.kotlin.ir.backend.js.utils.JsGenerationContext
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.expressions.IrCall

fun translateFunction(declaration: IrFunction, context: JsGenerationContext): FunctionDef {
    val body = declaration.body?.accept(IrElementToPyStatementTransformer(), context) ?: listOf(Pass)
    val args = declaration.valueParameters.map { valueParameter ->
        argImpl(
            arg = identifier(valueParameter.name.asString()),
            annotation = null,
            type_comment = null,
        )
    }

    return FunctionDef(
        name = identifier(declaration.name.asString()),
        args = argumentsImpl(
            posonlyargs = emptyList(),
            args = args,
            vararg = null,
            kwonlyargs = emptyList(),
            kw_defaults = emptyList(),
            kwarg = null,
            defaults = emptyList(),
        ),
        body = body,
        decorator_list = emptyList(),
        returns = null,
        type_comment = null,
    )
}

fun translateCall(
    expression: IrCall,
    context: JsGenerationContext,
    transformer: IrElementToPyExpressionTransformer
): expr {
    return transformer.visitCall(expression, context)
}
