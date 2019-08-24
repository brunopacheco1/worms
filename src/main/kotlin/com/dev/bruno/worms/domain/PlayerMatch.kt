package com.dev.bruno.worms.domain

import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class PlayerMatch(
        @ManyToOne
        var player: Player,
        @ManyToOne
        var match: Match
) : Persistable<Long>() {
    var wormLength: Int = 1
    var status: PlayerStatus = PlayerStatus.PLAYING
    @OneToMany(mappedBy = "player")
    var rounds: MutableSet<PlayerRound> = hashSetOf()
}