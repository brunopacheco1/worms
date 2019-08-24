package com.dev.bruno.worms.domain

import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class PlayerRound(
        var direction: Direction,
        @ManyToOne
        var player: PlayerMatch,
        @ManyToOne
        var round: Round
) : Persistable<Long>() {

    @OneToMany
    var currentPosition: MutableSet<MapPoint> = hashSetOf()
}