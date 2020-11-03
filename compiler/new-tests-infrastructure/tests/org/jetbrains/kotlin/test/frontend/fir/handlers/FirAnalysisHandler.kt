/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.test.frontend.fir.handlers

import org.jetbrains.kotlin.test.services.TestServices
import org.jetbrains.kotlin.test.frontend.fir.FirSourceArtifact
import org.jetbrains.kotlin.test.model.FrontendKind
import org.jetbrains.kotlin.test.model.FrontendResultsHandler
import org.jetbrains.kotlin.test.model.ResultingArtifact
import org.jetbrains.kotlin.test.model.TestModule
import java.io.File

abstract class FirAnalysisHandler(
    testServices: TestServices
) : FrontendResultsHandler<FirSourceArtifact>(testServices, FrontendKind.FIR) {
    final override fun processModule(module: TestModule, info: ResultingArtifact<FirSourceArtifact>) {
        processModule(module, info as FirSourceArtifact)
    }

    abstract fun processModule(module: TestModule, info: FirSourceArtifact)

    protected val File.nameWithoutFirExtension: String
        get() = nameWithoutExtension.removeSuffix(".fir")
}
