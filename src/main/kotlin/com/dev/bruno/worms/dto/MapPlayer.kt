package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.domain.MatchPlayerStatus

@Dto
data class MapPlayer(
        var playerId: Long,
        var status: MatchPlayerStatus = MatchPlayerStatus.PLAYING,
        var wormLength: Int = 0,
        var direction: Direction = Direction.DOWN,
        var position: List<MapPoint> = arrayListOf()
)