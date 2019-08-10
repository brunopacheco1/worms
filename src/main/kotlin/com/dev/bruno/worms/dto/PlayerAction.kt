package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.Direction

data class PlayerAction(
        val playerId: String,
        val direction: Direction
)