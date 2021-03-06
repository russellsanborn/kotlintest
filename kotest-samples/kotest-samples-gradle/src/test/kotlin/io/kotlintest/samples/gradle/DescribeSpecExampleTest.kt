package io.kotest.samples.gradle

import io.kotest.specs.DescribeSpec

class DescribeSpecExampleTest : DescribeSpec() {
    init {

        describe("some thing") {
            it("test name") {
                // test here
            }
            context("with some context") {
                it("test name") {
                    // test here
                }
                it("test name 2").config(invocations = 2) {
                    // test here
                }
                context("with some context") {
                    it("test name") {
                        // test here
                    }
                    it("test name 2").config(invocations = 2) {
                        // test here
                    }
                }
            }
        }

        describe("some other thing") {
            context("with some context") {
                it("test name") {
                    // test here
                }
                it("test name 2").config(invocations = 2) {
                    // test here
                }
                context("with some context") {
                    it("test name") {
                        // test here
                    }
                    it("test name 2").config(invocations = 2) {
                        // test here
                    }
                }
            }
        }
    }
}