package com.dev.bruno.worms.domain

data class PlayerMatch(
     val length: Int,
     val status: PlayerStatus,
     val player: Player,
     val match: Match
)