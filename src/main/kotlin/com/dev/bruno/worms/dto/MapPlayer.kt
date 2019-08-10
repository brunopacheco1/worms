package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.MapPoint

data class MapPlayer(
        val playerId: String,
        val status: MapPlayerStatus,
        val wormLength: Int,
        val position: Set<MapPoint>
)