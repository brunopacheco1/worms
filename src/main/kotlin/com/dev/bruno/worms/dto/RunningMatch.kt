package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.*

@Dto
data class RunningMatch(
        var id: Long,
        var wall: Wall,
        var opponentBody: OpponentBody,
        var difficulty: Difficulty,
        var playMode: PlayMode,
        var mapSize: Int,
        var players: List<Long>
)