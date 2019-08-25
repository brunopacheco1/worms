package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.domain.MapPoint
import com.dev.bruno.worms.domain.PlayerStatus

@Dto
data class MatchMapPlayer(
        var playerId: Long,
        var status: PlayerStatus,
        var wormLength: Int,
        var direction: Direction,
        var position: List<MapPoint>
)