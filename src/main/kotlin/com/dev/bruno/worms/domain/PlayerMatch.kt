package com.dev.bruno.worms.domain

import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class PlayerMatch(
        @ManyToOne
        val player: Player,
        @ManyToOne
        val match: Match
) : Persistable<Long>() {
    var wormLength: Int = 1
    var status: PlayerStatus = PlayerStatus.PLAYING
    @OneToMany(mappedBy = "player")
    val rounds: Set<PlayerRound> = hashSetOf()
}