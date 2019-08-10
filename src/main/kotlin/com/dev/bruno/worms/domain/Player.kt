package com.dev.bruno.worms.domain

data class Player(
        val id: String,
        val nickname: String,
        val matches: Set<PlayerMatch>
)