package com.dev.bruno.worms.domain

data class Match(
        val id: String,
        val status: MatchStatus,
        val gameMode: GameMode,
        val gamePlay: GamePlay,
        val difficulty: Difficulty,
        val playerMode: PlayerMode,
        val numberOfPlayers: Int,
        val players: Set<PlayerMatch>,
        val roundsCounter: Int,
        val rounds: Set<Round>
)