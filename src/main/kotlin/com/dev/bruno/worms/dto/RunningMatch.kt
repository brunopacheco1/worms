package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.*

@Dto
data class RunningMatch(
        val id: Long,
        var wall: Wall,
        var opponentBody: OpponentBody,
        var difficulty: Difficulty,
        var playMode: PlayMode,
        var mapSize: Int,
        val players: List<Long>
)