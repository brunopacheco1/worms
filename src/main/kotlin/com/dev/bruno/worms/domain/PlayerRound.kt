package com.dev.bruno.worms.domain

data class PlayerRound(
        val currentPosition: Set<MapPoint>,
        val direction: Direction,
        val player: PlayerMatch,
        val round: Round
)