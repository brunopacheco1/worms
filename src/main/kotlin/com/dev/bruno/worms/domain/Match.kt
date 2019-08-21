package com.dev.bruno.worms.domain

import javax.persistence.*

@Entity
data class Match(
        val gameMode: GameMode,
        val gamePlay: GamePlay,
        val difficulty: Difficulty,
        val playerMode: PlayerMode,
        val numberOfPlayers: Int,
        val mapSize: Int,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0
) {

    var status: MatchStatus = MatchStatus.WAITING_PLAYERS
    var roundsCounter: Int = 0
    @OneToMany(mappedBy = "match")
    val players: Set<PlayerMatch> = hashSetOf()
    @OneToMany(mappedBy = "match")
    val rounds: List<Round> = arrayListOf()
}