package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.domain.MapPoint
import com.dev.bruno.worms.domain.PlayerRoundStatus

@Dto
data class MatchMapPlayer(
        var playerId: Long?,
        var status: PlayerRoundStatus = PlayerRoundStatus.PLAYING,
        var wormLength: Int = 0,
        var direction: Direction = Direction.DOWN,
        var position: List<MapPoint> = arrayListOf()
)