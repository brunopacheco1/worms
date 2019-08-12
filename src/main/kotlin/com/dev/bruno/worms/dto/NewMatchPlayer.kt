package com.dev.bruno.worms.dto

class NewMatchPlayer {
    lateinit var playerId: String

    constructor(playerId: String) {
        this.playerId = playerId
    }

    constructor()
}