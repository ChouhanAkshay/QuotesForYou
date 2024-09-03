package com.example.baselineprofile

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startupNormal() = startup(CompilationMode.None())

    @Test
    fun startupBaselineProfile() = startup(CompilationMode.Partial())

    @Test
    fun scrollNormal() = addTaskAndScroll(CompilationMode.None())

    @Test
    fun scrollBaselineProfile() = addTaskAndScroll(CompilationMode.Partial())

    fun startup(compilationMode: CompilationMode) = benchmarkRule.measureRepeated(
        packageName = "com.example.quotesforyou",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
    }

    fun addTaskAndScroll(compilationMode: CompilationMode) = benchmarkRule.measureRepeated(
        packageName = "com.example.quotesforyou",
        metrics = listOf(FrameTimingMetric()),
        iterations = 1,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()

        device.wait(Until.hasObject(By.text("QuotesForYou")), 5000)
        device.waitForIdle()

        val list = device.findObject(By.res("quotes_list"))

        list.setGestureMargin(device.displayWidth / 5)
        list.fling(Direction.DOWN)

        device.waitForIdle()
    }
}