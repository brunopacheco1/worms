package com.dev.bruno.worms.domain

data class PlayerMatch(
     val wormLength: Int,
     val status: PlayerStatus,
     val player: Player,
     val match: Match
)