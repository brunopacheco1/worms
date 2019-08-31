package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.Difficulty
import com.dev.bruno.worms.domain.OpponentBody
import com.dev.bruno.worms.domain.PlayMode
import com.dev.bruno.worms.domain.Wall

@Dto
data class NewMatch(
        var wall: Wall,
        var opponentBody: OpponentBody,
        var difficulty: Difficulty,
        var playMode: PlayMode,
        var numberOfPlayers: Int,
        var mapSize: Int
)