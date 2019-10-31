package com.dev.bruno.worms.dto

import com.dev.bruno.worms.domain.MatchPlayerStatus

@Dto
data class MatchPlayerInfo(
        var id: Long,
        var nickname: String,
        var status: MatchPlayerStatus,
        var wormLength: Int
)