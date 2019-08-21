package com.dev.bruno.worms.domain

import javax.persistence.*

@Entity
data class Round(
        val status: RoundStatus,
        @ManyToOne
        val match: Match,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0
) {

    @OneToMany(mappedBy = "round")
    val players: Set<PlayerRound> = hashSetOf()
}