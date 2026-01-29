package com.checkmate.app.state

abstract class MiDynamicOffsetScrollFlagState(
    heightRange: IntRange, scrollValue: Int
) : MiScrollFlagState(heightRange, scrollValue) {

    protected abstract var scrollOffset: Float

}