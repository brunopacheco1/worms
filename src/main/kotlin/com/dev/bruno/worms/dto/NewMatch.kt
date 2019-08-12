package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.Difficulty
import com.dev.bruno.worms.domain.GameMode
import com.dev.bruno.worms.domain.GamePlay
import com.dev.bruno.worms.domain.PlayerMode

class NewMatch {
    lateinit var gameMode: GameMode
    lateinit var gamePlay: GamePlay
    lateinit var difficulty: Difficulty
    lateinit var playerMode: PlayerMode
    var numberOfPlayers: Int = 0
    var mapSize: Int = 0

    constructor(gameMode: GameMode, gamePlay: GamePlay, difficulty: Difficulty, playerMode: PlayerMode, numberOfPlayers: Int, mapSize: Int) {
        this.gameMode = gameMode
        this.gamePlay = gamePlay
        this.difficulty = difficulty
        this.playerMode = playerMode
        this.numberOfPlayers = numberOfPlayers
        this.mapSize = mapSize
    }

    constructor()
}