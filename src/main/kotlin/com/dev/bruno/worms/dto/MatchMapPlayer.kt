package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.MapPoint

@Dto
data class MatchMapPlayer(
        var playerId: Long?,
        var status: MatchMapPlayerStatus,
        var wormLength: Int,
        var position: Set<MapPoint>
)