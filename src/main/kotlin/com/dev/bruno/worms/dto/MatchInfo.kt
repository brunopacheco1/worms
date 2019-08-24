package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.*

@Dto
data class MatchInfo(
        var id: Long?,
        var status: MatchStatus,
        var gameMode: GameMode,
        var gamePlay: GamePlay,
        var difficulty: Difficulty,
        var playerMode: PlayerMode,
        var numberOfPlayers: Int,
        var mapSize: Int,
        var players: List<PlayerInfo>
)