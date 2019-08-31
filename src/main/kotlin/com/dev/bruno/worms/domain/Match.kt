package com.dev.bruno.worms.domain

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
data class Match(
        var wall: Wall,
        var opponentBody: OpponentBody,
        var difficulty: Difficulty,
        var playMode: PlayMode,
        var numberOfPlayers: Int,
        var mapSize: Int
) : Persistable<Long>() {

    var status: MatchStatus = MatchStatus.WAITING_PLAYERS

    @OneToMany(
            mappedBy = "match",
            cascade = [(CascadeType.ALL)],
            orphanRemoval = true
    )
    var players: MutableSet<PlayerMatch> = hashSetOf()

    @OneToMany(
            mappedBy = "match",
            cascade = [(CascadeType.ALL)],
            orphanRemoval = true
    )
    var rounds: MutableList<Round> = arrayListOf()
}
