package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.MatchStatus

@Dto
data class Map(
        var matchId: Long,
        var roundCounter: Int,
        var mapSize: Int,
        var players: MutableList<MapPlayer>,
        var foodPosition: MapPoint,
        var status: MatchStatus = MatchStatus.RUNNING
)
