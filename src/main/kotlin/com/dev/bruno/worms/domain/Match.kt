package com.dev.bruno.worms.domain

import javax.persistence.*

@Entity
data class Match(
        val gameMode: GameMode,
        val gamePlay: GamePlay,
        val difficulty: Difficulty,
        val playerMode: PlayerMode,
        val numberOfPlayers: Int,
        val mapSize: Int
) : Persistable<Long>() {

    var status: MatchStatus = MatchStatus.WAITING_PLAYERS
    var roundsCounter: Int = 0
    @OneToMany(mappedBy = "match", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    val players: Set<PlayerMatch> = hashSetOf()
    @OneToMany(mappedBy = "match", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    val rounds: List<Round> = arrayListOf()
}