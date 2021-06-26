/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.declarations

import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.fir.FirPureAbstractElement
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference
import org.jetbrains.kotlin.fir.scopes.FirScopeProvider
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.fir.visitors.*

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

abstract class FirRegularClass : FirPureAbstractElement(), FirMemberDeclaration, FirTypeParameterRefsOwner, FirControlFlowGraphOwner, FirClass<FirRegularClass> {
    abstract override val source: FirSourceElement?
    abstract override val session: FirSession
    abstract override val resolvePhase: FirResolvePhase
    abstract override val origin: FirDeclarationOrigin
    abstract override val attributes: FirDeclarationAttributes
    abstract override val annotations: List<FirAnnotationCall>
    abstract override val typeParameters: List<FirTypeParameterRef>
    abstract override val status: FirDeclarationStatus
    abstract override val controlFlowGraphReference: FirControlFlowGraphReference?
    abstract override val classKind: ClassKind
    abstract override val declarations: List<FirDeclaration>
    abstract override val scopeProvider: FirScopeProvider
    abstract val name: Name
    abstract override val symbol: FirRegularClassSymbol
    abstract val companionObject: FirRegularClass?
    abstract val hasLazyNestedClassifiers: Boolean
    abstract override val superTypeRefs: List<FirTypeRef>

    override fun <R, D> accept(visitor: FirVisitor<R, D>, data: D): R = visitor.visitRegularClass(this, data)

    abstract override fun replaceResolvePhase(newResolvePhase: FirResolvePhase)

    abstract override fun replaceControlFlowGraphReference(newControlFlowGraphReference: FirControlFlowGraphReference?)

    abstract override fun replaceSuperTypeRefs(newSuperTypeRefs: List<FirTypeRef>)

    abstract override fun <D> transformAnnotations(transformer: FirTransformer<D>, data: D): FirRegularClass

    abstract override fun <D> transformTypeParameters(transformer: FirTransformer<D>, data: D): FirRegularClass

    abstract override fun <D> transformStatus(transformer: FirTransformer<D>, data: D): FirRegularClass

    abstract override fun <D> transformDeclarations(transformer: FirTransformer<D>, data: D): FirRegularClass

    abstract fun <D> transformCompanionObject(transformer: FirTransformer<D>, data: D): FirRegularClass

    abstract override fun <D> transformSuperTypeRefs(transformer: FirTransformer<D>, data: D): FirRegularClass
}
