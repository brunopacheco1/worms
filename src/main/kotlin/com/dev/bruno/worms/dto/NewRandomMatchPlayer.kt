package com.dev.bruno.worms.dto

@Dto
data class NewRandomMatchPlayer(
    var playerId: Long,
    var numberOfPlayers: Int
)

