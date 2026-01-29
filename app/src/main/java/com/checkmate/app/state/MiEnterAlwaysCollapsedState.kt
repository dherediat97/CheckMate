package com.checkmate.app.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.setValue
import kotlin.text.get

class MiEnterAlwaysCollapsedState(
    heightRange: IntRange, scrollValue: Int = 0, scrollOffset: Float = 0f
) : MiDynamicOffsetScrollFlagState(heightRange, scrollValue) {

    override var scrollOffset by mutableFloatStateOf(scrollOffset.coerceIn(0f, minHeight.toFloat()))

    override val offset: Float
        get() = -scrollOffset

    override val height: Float
        get() = (maxHeight.toFloat() - scrollValue).coerceIn(
            minHeight.toFloat(), maxHeight.toFloat()
        )

    override var scrollValue: Int
        get() = scrollFlagValue
        set(value) {
            val delta = (scrollFlagValue - value).toFloat().let {
                if (it < 0 && height > minHeight) {
                    (height - minHeight + it).coerceAtMost(0f)
                } else it
            }
            scrollOffset = (scrollOffset - delta).coerceIn(0f, minHeight.toFloat())
            scrollFlagValue = value.coerceAtLeast(0)
        }

    companion object {
        val Saver = run {

            val minHeightKey = "MinHeight"
            val maxHeightKey = "MaxHeight"
            val scrollValueKey = "ScrollValue"
            val scrollOffsetKey = "ScrollOffset"

            mapSaver(save = {
                mapOf(
                    minHeightKey to it.minHeight,
                    maxHeightKey to it.maxHeight,
                    scrollValueKey to it.scrollValue,
                    scrollOffsetKey to it.scrollOffset
                )
            }, restore = {
                MiEnterAlwaysCollapsedState(
                    heightRange = (it[minHeightKey] as Int)..(it[maxHeightKey] as Int),
                    scrollValue = it[scrollValueKey] as Int,
                    scrollOffset = it[scrollOffsetKey] as Float,
                )
            })
        }
    }
}