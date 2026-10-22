package com.gameoverlay.macro.service

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.os.Handler
import android.os.Looper
import android.view.accessibility.AccessibilityEvent
import kotlin.random.Random

class MacroAccessibilityService : AccessibilityService() {

    companion object {
        var instance: MacroAccessibilityService? = null
            private set
    }

    private val mainHandler = Handler(Looper.getMainLooper())

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
    }

    override fun onDestroy() {
        super.onDestroy()
        if (instance == this) instance = null
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() {}

    /**
     * Injects coordinate-based touch tap with optional anti-detection jitter
     */
    fun dispatchTap(x: Float, y: Float, holdDurationMs: Long = 50L, jitterRadius: Float = 4f, onComplete: (() -> Unit)? = null) {
        val finalX = if (jitterRadius > 0) x + Random.nextDouble(-jitterRadius.toDouble(), jitterRadius.toDouble()).toFloat() else x
        val finalY = if (jitterRadius > 0) y + Random.nextDouble(-jitterRadius.toDouble(), jitterRadius.toDouble()).toFloat() else y

        val clickPath = Path().apply {
            moveTo(finalX, finalY)
        }

        val stroke = GestureDescription.StrokeDescription(clickPath, 0L, holdDurationMs.coerceAtLeast(10L))
        val gesture = GestureDescription.Builder().addStroke(stroke).build()

        dispatchGesture(gesture, object : GestureResultCallback() {
            override fun onCompleted(gestureDescription: GestureDescription?) {
                onComplete?.invoke()
            }
            override fun onCancelled(gestureDescription: GestureDescription?) {
                onComplete?.invoke()
            }
        }, null)
    }

    /**
     * Executes sequence of taps
     */
    fun executeSequence(taps: List<Pair<Float, Float>>, delayMs: Long = 200L) {
        var cumulative = 0L
        taps.forEach { (x, y) ->
            mainHandler.postDelayed({
                dispatchTap(x, y)
            }, cumulative)
            cumulative += delayMs
        }
    }
}
