package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.MapPoint
import com.dev.bruno.worms.domain.MatchStatus

@Dto
data class MatchMap(
        var matchId: Long?,
        var roundCounter: Int,
        var players: MutableList<MatchMapPlayer>,
        var foodPosition: MapPoint,
        var status: MatchStatus = MatchStatus.RUNNING
)