package com.dev.bruno.worms.domain

import javax.persistence.*

@Entity
class PlayerMatch(
        @ManyToOne
        val player: Player,
        @ManyToOne
        val match: Match) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var wormLength: Int = 1
    var status: PlayerStatus = PlayerStatus.PLAYING
    @OneToMany(mappedBy = "player")
    val rounds: Set<PlayerRound> = hashSetOf()
}