package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.*

@Dto
data class MatchInfo(
        var id: Long?,
        var status: MatchStatus,
        var wall: Wall,
        var opponentBody: OpponentBody,
        var difficulty: Difficulty,
        var playMode: PlayMode,
        var numberOfPlayers: Int,
        var mapSize: Int,
        var players: List<MatchPlayerInfo>
)