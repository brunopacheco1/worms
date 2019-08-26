package com.dev.bruno.worms.domain

import javax.persistence.*

@Entity
data class Round(
        var roundOrder: Long,
        @ManyToOne
        var match: Match,
        @Embedded
        var foodPosition: MapPoint
) : Persistable<Long>() {

    var status = RoundStatus.WAITING_PLAYERS

    @OneToMany(mappedBy = "round", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    var players: MutableList<PlayerRound> = arrayListOf()
}