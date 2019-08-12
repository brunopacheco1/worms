package com.dev.bruno.worms.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Match(
        val gameMode: GameMode,
        val gamePlay: GamePlay,
        val difficulty: Difficulty,
        val playerMode: PlayerMode,
        val numberOfPlayers: Int,
        val mapSize: Int,
        var roundsCounter: Int = 0

) {
    @Id
    lateinit var id: String

    @OneToMany(mappedBy = "match")
    val players: Set<PlayerMatch> = hashSetOf()

    @OneToMany(mappedBy = "match")
    val rounds: Set<Round> = hashSetOf()

    var status: MatchStatus = MatchStatus.WAITING_PLAYERS
}