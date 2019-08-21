package com.dev.bruno.worms.domain

import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class PlayerRound(
        var direction: Direction,
        @ManyToOne
        val player: PlayerMatch,
        @ManyToOne
        val round: Round
) : Persistable<Long>() {

    @OneToMany
    val currentPosition: Set<MapPoint> = hashSetOf()
}