package com.dev.bruno.worms.domain

data class Round(
        val id: Int,
        val status: RoundStatus,
        val players: Set<PlayerRound>
)