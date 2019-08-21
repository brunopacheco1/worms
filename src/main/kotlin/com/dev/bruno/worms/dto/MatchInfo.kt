package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.Difficulty
import com.dev.bruno.worms.domain.GameMode
import com.dev.bruno.worms.domain.GamePlay
import com.dev.bruno.worms.domain.PlayerMode

@Dto
data class MatchInfo(
        val id: Long?,
        val gameMode: GameMode,
        val gamePlay: GamePlay,
        val difficulty: Difficulty,
        val playerMode: PlayerMode,
        val numberOfPlayers: Int,
        val mapSize: Int,
        val players: List<PlayerInfo>
)