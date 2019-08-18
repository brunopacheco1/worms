package com.dev.bruno.worms.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Match(
        val gameMode: GameMode,
        val gamePlay: GamePlay,
        val difficulty: Difficulty,
        val playerMode: PlayerMode,
        val numberOfPlayers: Int,
        val mapSize: Int,
        var roundsCounter: Int = 0,
        var status: MatchStatus = MatchStatus.WAITING_PLAYERS,
        @OneToMany(mappedBy = "match")
        val players: Set<PlayerMatch> = hashSetOf(),
        @OneToMany(mappedBy = "match")
        val rounds: List<Round> = arrayListOf()
) {
    @Id
    @GeneratedValue
    var id: Long = 0
}