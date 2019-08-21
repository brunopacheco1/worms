package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.MapPoint

@Dto
data class MatchMapPlayer(
        val playerId: Long?,
        val status: MatchMapPlayerStatus,
        val wormLength: Int,
        val position: Set<MapPoint>
)