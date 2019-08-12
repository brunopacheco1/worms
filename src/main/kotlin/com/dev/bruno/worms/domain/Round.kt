package com.dev.bruno.worms.domain

import javax.persistence.*

@Entity
data class Round(
        @Id
        @GeneratedValue
        val id: Long,
        val status: RoundStatus,
        @ManyToOne
        val match: Match,
        @OneToMany(mappedBy = "round")
        val players: Set<PlayerRound> = hashSetOf()
)