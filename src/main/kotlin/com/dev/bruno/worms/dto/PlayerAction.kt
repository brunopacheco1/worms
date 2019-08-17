package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.Direction

class PlayerAction {
    var playerId: Long = 0
    var direction: Direction = Direction.UP

    constructor(playerId: Long, direction: Direction) {
        this.playerId = playerId
        this.direction = direction
    }

    constructor()
}