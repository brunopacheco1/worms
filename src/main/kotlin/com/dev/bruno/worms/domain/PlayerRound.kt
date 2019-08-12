package com.dev.bruno.worms.domain

import javax.persistence.*

@Entity
data class PlayerRound(
        @Id
        @GeneratedValue
        val id: Long,
        @OneToMany
        val currentPosition: Set<MapPoint> = hashSetOf(),
        val direction: Direction,
        @ManyToOne
        val player: PlayerMatch,
        @ManyToOne
        val round: Round
)