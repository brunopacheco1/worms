package com.dev.bruno.worms.dto

@Dto
data class RunningMatch(
        val id: Long?,
        val mapSize: Int,
        val players: Set<Long?>
)