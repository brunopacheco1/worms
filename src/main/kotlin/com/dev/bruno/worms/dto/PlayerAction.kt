package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.Direction

class PlayerAction {
    lateinit var playerId: String
    lateinit var direction: Direction

    constructor(playerId: String, direction: Direction) {
        this.playerId = playerId
        this.direction = direction
    }

    constructor()
}