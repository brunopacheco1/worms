package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.MatchStatus

@Dto
data class MatchMap(
        var matchId: Long,
        var roundCounter: Int,
        var mapSize: Int,
        var players: List<MatchMapPlayer>,
        var foodPosition: MapPoint,
        var status: MatchStatus = MatchStatus.RUNNING
)
