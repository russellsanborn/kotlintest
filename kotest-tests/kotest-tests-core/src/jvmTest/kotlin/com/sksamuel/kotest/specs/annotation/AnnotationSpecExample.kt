package com.sksamuel.kotest.specs.annotation

import io.kotest.shouldBe
import io.kotest.specs.AnnotationSpec

class AnnotationSpecExample : AnnotationSpec() {

  @Test
  fun test1() {
    1 shouldBe 1
  }

  @Test
  fun test2() {
    3 shouldBe 3
  }
}