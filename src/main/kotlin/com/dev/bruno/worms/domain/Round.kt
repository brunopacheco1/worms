package com.dev.bruno.worms.domain

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class Round(
        val roundOrder: Long,
        @ManyToOne
        val match: Match
) : Persistable<Long>() {

    var status = RoundStatus.WAITING_PLAYERS

    @OneToMany(mappedBy = "round", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    val players: Set<PlayerRound> = hashSetOf()
}