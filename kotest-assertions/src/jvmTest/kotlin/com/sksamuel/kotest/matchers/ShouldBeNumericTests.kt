package com.sksamuel.kotest.matchers

import io.kotest.core.spec.style.WordSpec
import io.kotest.shouldBe

class ShouldBeNumericTests : WordSpec({
  "should be" should {
    "support combinations of numerics" {

      val v1: Int = Foo.JavaLong.toInt()
      val v2 = java.lang.Integer.valueOf(42)
      val v3 = Foo.JavaLong
      val v4 = java.lang.Long.valueOf(42)
      val v5 = Foo.JavaLong
      val v6 = 42.0
      val v7 = Foo.JavaDouble

      v1 shouldBe 42
      v2 shouldBe 42
      v3 shouldBe 42
      v4 shouldBe 42
      v5 shouldBe 42
      42 shouldBe v1
      42 shouldBe v2
      42 shouldBe v3
      42 shouldBe v4
      42 shouldBe v5
      v6 shouldBe 42F
      v7 shouldBe 42F
      42F shouldBe v6
      42F shouldBe v7
    }
  }
})
