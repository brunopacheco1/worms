package com.dev.bruno.worms.domain

import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
data class MatchPlayer(
        @ManyToOne
        var player: Player,
        @ManyToOne
        var match: Match
) : Persistable<Long>() {

    var wormLength = 2

    var status = MatchPlayerStatus.PLAYING
}
