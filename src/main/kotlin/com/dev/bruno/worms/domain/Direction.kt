package com.dev.bruno.worms.domain

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    fun isOpposite(other: Direction): Boolean {
        return when (this) {
            LEFT -> other == RIGHT
            RIGHT -> other == LEFT
            UP -> other == DOWN
            DOWN -> other == UP
        }
    }
}