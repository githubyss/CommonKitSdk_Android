package com.githubyss.mobile.common.kit.entity


/**
 * Coordinate
 * Element x and y are in pixels.
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/15 11:31:09
 */
data class Coordinate(var x: Float, var y: Float) {
    /**
     * 等比例缩放
     *
     * @param scale Scale to scale.
     * @return Scaled coordinate.
     */
    fun scale(scale: Float): Coordinate {
        return Coordinate(x * scale, y * scale)
    }
}