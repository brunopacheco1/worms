package com.dev.bruno.worms.dto

class NewMatchPlayer {
    var playerId: Long = 0

    constructor(playerId: Long) {
        this.playerId = playerId
    }

    constructor()
}