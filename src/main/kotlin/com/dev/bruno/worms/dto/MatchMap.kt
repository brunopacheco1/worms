package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.MapPoint

@Dto
data class MatchMap (
        val matchId: String,
        val players: Set<MatchMapPlayer>,
        val foodPosition: MapPoint
)