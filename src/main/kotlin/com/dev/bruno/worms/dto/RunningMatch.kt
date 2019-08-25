package com.dev.bruno.worms.dto

@Dto
data class RunningMatch(
        val id: Long?,
        val players: Set<Long?>
)