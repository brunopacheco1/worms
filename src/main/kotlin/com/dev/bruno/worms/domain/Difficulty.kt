package com.dev.bruno.worms.domain

enum class Difficulty(val tickRate: Long) {
    EASY(1000),
    MEDIUM(500),
    HARD(250);
}