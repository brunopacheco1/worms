package com.dev.bruno.worms.domain

data class Player(
        val nickname: String,
        val matches: Set<PlayerMatch> = hashSetOf()
) {
    lateinit var id: String
}