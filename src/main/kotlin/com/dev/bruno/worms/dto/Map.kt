package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.MapPoint

data class Map (
        val players: Set<MapPlayer>,
        val foodPosition: MapPoint
)