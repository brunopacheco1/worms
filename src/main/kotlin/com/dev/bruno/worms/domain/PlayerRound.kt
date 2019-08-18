package com.dev.bruno.worms.domain

import javax.persistence.*

@Entity
class PlayerRound(
        var direction: Direction,
        @ManyToOne
        val player: PlayerMatch,
        @ManyToOne
        val round: Round
) {

        @Id
        @GeneratedValue
        val id: Long = 0

        @OneToMany
        val currentPosition: Set<MapPoint> = hashSetOf()
}