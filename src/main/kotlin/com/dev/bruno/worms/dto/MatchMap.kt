package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.MapPoint

@Dto
data class MatchMap(
        var matchId: Long?,
        var roundCounter: Int,
        var players: Set<MatchMapPlayer>,
        var foodPosition: MapPoint
)