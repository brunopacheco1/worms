package com.dev.bruno.worms.domain

enum class Difficulty {
    EASY(1000),
    NORMAL(500),
    HARD(250);

    val tickRate: Long

    constructor(tickRate: Long) {
        this.tickRate = tickRate
    }
}