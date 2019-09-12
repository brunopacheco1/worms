package com.dev.bruno.worms.domain

import javax.persistence.*

@Entity(name = "MATCH_PLAYER")
data class MatchPlayer(
        @JoinColumn(name = "player_id")
        @ManyToOne
        var player: Player,
        @JoinColumn(name = "match_id")
        @ManyToOne
        var match: Match
) : Persistable<Long>() {

    @Column(name = "worm_length")
    var wormLength = 2

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status = MatchPlayerStatus.PLAYING
}
