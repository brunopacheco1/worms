package com.dev.bruno.worms.domain

import javax.persistence.*

@Entity
data class PlayerMatch(
        @Id
        @GeneratedValue
        val id: Long,
        val wormLength: Int,
        val status: PlayerStatus,
        @ManyToOne
        val player: Player,
        @ManyToOne
        val match: Match,
        @OneToMany(mappedBy = "player")
        val rounds: Set<PlayerRound> = hashSetOf()
)