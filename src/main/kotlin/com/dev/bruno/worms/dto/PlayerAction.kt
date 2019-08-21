package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.Direction

@Dto
data class PlayerAction(
        val playerId: Long,
        val direction: Direction
)