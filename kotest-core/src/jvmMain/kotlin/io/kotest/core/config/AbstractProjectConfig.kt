package io.kotest.core.config

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.LexicographicSpecExecutionOrder
import io.kotest.core.spec.SpecExecutionOrder
import io.kotest.core.test.AssertionMode
import io.kotest.core.test.TestCaseOrder
import io.kotest.core.extensions.ProjectLevelExtension
import io.kotest.core.extensions.ProjectLevelFilter
import io.kotest.core.extensions.ProjectListener
import io.kotest.core.extensions.TestListener
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

/**
 * Project-wide configuration. Extensions returned by an
 * instance of this class will be applied to all [SpecClass] and [TestCase]s.
 *
 * Create an object that is derived from this class, name the object `ProjectConfig`
 * and place it in your classpath in a package called `io.kotest.provided`.
 *
 * Kotest will detect its presence and use it when executing tests.
 *
 * Note: This is a breaking change from versions 2.0 and before, in which Kotest would
 * scan the classpath for instances of this class. It no longer does that, in favour
 * of the predefined package name + classname.
 */
abstract class AbstractProjectConfig {

   /**
    * List of project wide extensions, ie instances of [ProjectLevelExtension]
    */
   open fun extensions(): List<ProjectLevelExtension> = emptyList()

   /**
    * List of project wide [TestListener] instances.
    */
   open fun listeners(): List<TestListener> = emptyList()

   /**
    * List of project wide [ProjectListener] instances.
    */
   open fun projectListeners(): List<ProjectListener> = emptyList()

   /**
    * List of project wide [TestCaseFilter] instances.
    */
   open fun filters(): List<ProjectLevelFilter> = emptyList()

   /**
    * Override this function and return an instance of [SpecExecutionOrder] which will
    * be used to sort specs before execution.
    *
    * Implementations are currently:
    *  - [LexicographicSpecExecutionOrder]
    *  - [FailureFirstSpecExecutionOrder]
    *  - [RandomSpecExecutionOrder]
    */
   @Deprecated("use the val version")
   open fun specExecutionOrder(): SpecExecutionOrder? = null

   open val specExecutionOrder: SpecExecutionOrder? = null

   /**
    * The [IsolationMode] set here will be applied if the isolation mode in a spec is null.
    */
   @Deprecated("use the val version")
   open fun isolationMode(): IsolationMode? = null

   open val isolationMode: IsolationMode? = null

   /**
    * A global timeout that is applied to all tests if not null.
    * Tests which define their own timeout will override this.
    * The value here is in millis
    */
   @UseExperimental(ExperimentalTime::class)
   open val timeout: Duration? = null

   /**
    * Override this function and return a number greater than 1 if you wish to
    * enable parallel execution of tests. The number returned is the number of
    * concurrently executing specs.
    *
    * An alternative way to enable this is the system property kotest.parallelism
    * which will always (if defined) take priority over the value here.
    */
   @Deprecated("use the val version")
   open fun parallelism(): Int = 1

   open val parallelism: Int = 1

   /**
    * When set to true, failed specs are written to a file called spec_failures.
    * This file is used on subsequent test runs to run the failed specs first.
    *
    * To enable this feature, set this to true, or set the system property
    * 'kotest.write.specfailures=true'
    */
   @Deprecated("use the val version")
   open fun writeSpecFailureFile(): Boolean = false

   open val writeSpecFailureFile: Boolean = false

   /**
    * Sets the order of top level tests in a spec.
    * The value set here will be used unless overriden in a [SpecClass].
    * The value in a [SpecClass] is always taken in preference to the value here.
    * Nested tests will always be executed in discovery order.
    *
    * If this function returns null then the default of Sequential
    * will be used.
    */
   @Deprecated("use the val version")
   open fun testCaseOrder(): TestCaseOrder? = null

   val testCaseOrder: TestCaseOrder? = null

   /**
    * Override this value and set it to true if you want all tests to behave as if they
    * were operating in an [assertSoftly] block.
    */
   open val globalAssertSoftly: Boolean = false

   /**
    * Override this value and set it to true if you want the build to be marked as failed
    * if there was one or more tests that were disabled/ignored.
    */
   open val failOnIgnoredTests: Boolean = false

   /**
    * Override this value to set a global [AssertionMode].
    * If a [SpecClass] sets an assertion mode, then the spec will override.
    */
   open val assertionMode: AssertionMode? = null

   /**
    * Executed before the first test of the project, but after the
    * [ProjectListener.beforeProject] methods.
    */
   open fun beforeAll() {}

   /**
    * Executed after the last test of the project, but before the
    * [ProjectListener.afterProject] methods.
    */
   open fun afterAll() {}
}

