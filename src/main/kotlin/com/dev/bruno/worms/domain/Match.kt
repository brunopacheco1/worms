package com.dev.bruno.worms.domain

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity("MATCH")
data class Match(
	@Field("wall")
	@Enumerated(EnumType.STRING)
        var wall: Wall,
	@Field("opponent_body")
	@Enumerated(EnumType.STRING)
        var opponentBody: OpponentBody,
	@Field("difficulty")
	@Enumerated(EnumType.STRING)
        var difficulty: Difficulty,
	@Field("play_mode")
	@Enumerated(EnumType.STRING)
        var playMode: PlayMode,
	@Field("number_of_players")
        var numberOfPlayers: Int,
	@Field("map_size")
        var mapSize: Int
) : Persistable<Long>() {

	@Field("status")
    var status: MatchStatus = MatchStatus.WAITING_PLAYERS

    @OneToMany(
            mappedBy = "match",
            cascade = [(CascadeType.ALL)],
            orphanRemoval = true
    )
    var players: MutableList<MatchPlayer> = arrayListOf()
}
