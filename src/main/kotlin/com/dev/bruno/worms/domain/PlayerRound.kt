package com.dev.bruno.worms.domain

import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
data class PlayerRound(
        @ManyToOne
        var player: PlayerMatch,
        @ManyToOne
        var round: Round
) : Persistable<Long>() {

    var wormLength = 2

    var direction = Direction.UP

    var status = PlayerRoundStatus.PLAYING

    @ElementCollection
    var currentPosition: MutableList<MapPoint> = arrayListOf()
}
