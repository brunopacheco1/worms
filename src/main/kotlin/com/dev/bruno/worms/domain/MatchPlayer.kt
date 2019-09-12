package com.dev.bruno.worms.domain

import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity("MATCH_PLAYER")
data class MatchPlayer(
        @JoinCollumn("player_id")
	@ManyToOne
        var player: Player,
	@JoinCollumn("match_id")
        @ManyToOne
        var match: Match
) : Persistable<Long>() {

	@Field("worm_length")
    var wormLength = 2

    @Field("status")
    @Enumerated(EnumType.STRING)
    var status = MatchPlayerStatus.PLAYING
}
