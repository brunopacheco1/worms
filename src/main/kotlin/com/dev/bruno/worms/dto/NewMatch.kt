package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.Difficulty
import com.dev.bruno.worms.domain.GameMode
import com.dev.bruno.worms.domain.GamePlay
import com.dev.bruno.worms.domain.PlayerMode

@Dto
data class NewMatch(
        var gameMode: GameMode,
        var gamePlay: GamePlay,
        var difficulty: Difficulty,
        var playerMode: PlayerMode,
        var numberOfPlayers: Int,
        var mapSize: Int
)