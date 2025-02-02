/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.python.test.ir.semantics;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TargetBackend;
import org.jetbrains.kotlin.test.TestMetadata;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("compiler/testData/codegen/boxError")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class IrPythonCodegenBoxErrorTestGenerated extends AbstractIrPythonCodegenBoxErrorTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest0(this::doTest, TargetBackend.PYTHON, testDataFilePath);
    }

    public void testAllFilesPresentInBoxError() throws Exception {
        KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/codegen/boxError"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.PYTHON, true);
    }

    @TestMetadata("compiler/testData/codegen/boxError/semantic")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Semantic extends AbstractIrPythonCodegenBoxErrorTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest0(this::doTest, TargetBackend.PYTHON, testDataFilePath);
        }

        public void testAllFilesPresentInSemantic() throws Exception {
            KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/codegen/boxError/semantic"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.PYTHON, true);
        }

        @TestMetadata("castToErrorType.kt")
        public void testCastToErrorType() throws Exception {
            runTest("compiler/testData/codegen/boxError/semantic/castToErrorType.kt");
        }

        @TestMetadata("catchErrorType.kt")
        public void testCatchErrorType() throws Exception {
            runTest("compiler/testData/codegen/boxError/semantic/catchErrorType.kt");
        }

        @TestMetadata("evaluationOrder.kt")
        public void testEvaluationOrder() throws Exception {
            runTest("compiler/testData/codegen/boxError/semantic/evaluationOrder.kt");
        }

        @TestMetadata("mismatchTypeParameters.kt")
        public void testMismatchTypeParameters() throws Exception {
            runTest("compiler/testData/codegen/boxError/semantic/mismatchTypeParameters.kt");
        }

        @TestMetadata("missedBody.kt")
        public void testMissedBody() throws Exception {
            runTest("compiler/testData/codegen/boxError/semantic/missedBody.kt");
        }

        @TestMetadata("reifiedNonInline.kt")
        public void testReifiedNonInline() throws Exception {
            runTest("compiler/testData/codegen/boxError/semantic/reifiedNonInline.kt");
        }

        @TestMetadata("reifiedWithWrongArguments.kt")
        public void testReifiedWithWrongArguments() throws Exception {
            runTest("compiler/testData/codegen/boxError/semantic/reifiedWithWrongArguments.kt");
        }

        @TestMetadata("typeMismatch.kt")
        public void testTypeMismatch() throws Exception {
            runTest("compiler/testData/codegen/boxError/semantic/typeMismatch.kt");
        }

        @TestMetadata("unmatchedArguments.kt")
        public void testUnmatchedArguments() throws Exception {
            runTest("compiler/testData/codegen/boxError/semantic/unmatchedArguments.kt");
        }

        @TestMetadata("unresolvedFunctionReferece.kt")
        public void testUnresolvedFunctionReferece() throws Exception {
            runTest("compiler/testData/codegen/boxError/semantic/unresolvedFunctionReferece.kt");
        }
    }

    @TestMetadata("compiler/testData/codegen/boxError/syntax")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Syntax extends AbstractIrPythonCodegenBoxErrorTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest0(this::doTest, TargetBackend.PYTHON, testDataFilePath);
        }

        public void testAllFilesPresentInSyntax() throws Exception {
            KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/codegen/boxError/syntax"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.PYTHON, true);
        }

        @TestMetadata("arrowReference.kt")
        public void testArrowReference() throws Exception {
            runTest("compiler/testData/codegen/boxError/syntax/arrowReference.kt");
        }

        @TestMetadata("evaluationOrder.kt")
        public void testEvaluationOrder() throws Exception {
            runTest("compiler/testData/codegen/boxError/syntax/evaluationOrder.kt");
        }

        @TestMetadata("incorectLexicalName.kt")
        public void testIncorectLexicalName() throws Exception {
            runTest("compiler/testData/codegen/boxError/syntax/incorectLexicalName.kt");
        }

        @TestMetadata("missedArgument.kt")
        public void testMissedArgument() throws Exception {
            runTest("compiler/testData/codegen/boxError/syntax/missedArgument.kt");
        }
    }
}
