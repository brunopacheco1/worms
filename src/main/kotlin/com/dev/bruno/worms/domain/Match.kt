package com.dev.bruno.worms.domain

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
data class Match(
        var gameMode: GameMode,
        var gamePlay: GamePlay,
        var difficulty: Difficulty,
        var playerMode: PlayerMode,
        var numberOfPlayers: Int,
        var mapSize: Int
) : Persistable<Long>() {

    var status: MatchStatus = MatchStatus.WAITING_PLAYERS
    @OneToMany(mappedBy = "match", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    var players: MutableSet<PlayerMatch> = hashSetOf()
    @OneToMany(mappedBy = "match", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    var rounds: MutableList<Round> = arrayListOf()
}