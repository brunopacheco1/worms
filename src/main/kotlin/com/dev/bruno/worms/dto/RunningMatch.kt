package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.Difficulty
import com.dev.bruno.worms.domain.GameMode
import com.dev.bruno.worms.domain.GamePlay
import com.dev.bruno.worms.domain.PlayerMode

@Dto
data class RunningMatch(
        val id: Long?,
        var gameMode: GameMode,
        var gamePlay: GamePlay,
        var difficulty: Difficulty,
        var playerMode: PlayerMode,
        val mapSize: Int,
        val players: List<Long?>
)