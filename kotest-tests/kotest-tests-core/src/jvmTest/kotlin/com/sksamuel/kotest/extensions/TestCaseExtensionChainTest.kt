package com.sksamuel.kotest.extensions

import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.core.extensions.SpecLevelExtension
import io.kotest.core.extensions.TestCaseExtension
import io.kotest.core.spec.style.StringSpec

class TestCaseExtensionChainTest : StringSpec() {

  object MyExt1 : TestCaseExtension {
    override suspend fun intercept(testCase: TestCase, execute: suspend (TestCase, suspend (TestResult) -> Unit) -> Unit, complete: suspend (TestResult) -> Unit) {
      if (testCase.description.name == "test1")
        complete(TestResult.Ignored)
      else
        execute(testCase) { complete(it) }
    }
  }

  object MyExt2 : TestCaseExtension {
    override suspend fun intercept(testCase: TestCase, execute: suspend (TestCase, suspend (TestResult) -> Unit) -> Unit, complete: suspend (TestResult) -> Unit) {
      if (testCase.description.name == "test2")
        complete(TestResult.Ignored)
      else
        execute(testCase) { complete(it) }
    }
  }

  override fun extensions(): List<SpecLevelExtension> = listOf(MyExt1, MyExt2)

  init {
    "test1" {
      // this exception should not be thrown as the first interceptor should ignore it
      throw RuntimeException()
    }
    "test2" {
      // this exception should not be thrown as the second interceptor should ignore it
      throw RuntimeException()
    }
    "test3" {

    }
  }
}
