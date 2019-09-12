package com.dev.bruno.worms.domain

import javax.persistence.*

@Entity(name = "MATCH")
data class Match(
        @Column(name = "wall")
        @Enumerated(EnumType.STRING)
        var wall: Wall,
        @Column(name = "opponent_body")
        @Enumerated(EnumType.STRING)
        var opponentBody: OpponentBody,
        @Column(name = "difficulty")
        @Enumerated(EnumType.STRING)
        var difficulty: Difficulty,
        @Column(name = "play_mode")
        @Enumerated(EnumType.STRING)
        var playMode: PlayMode,
        @Column(name = "number_of_players")
        var numberOfPlayers: Int,
        @Column(name = "map_size")
        var mapSize: Int
) : Persistable<Long>() {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: MatchStatus = MatchStatus.WAITING_PLAYERS

    @OneToMany(
            mappedBy = "match",
            cascade = [(CascadeType.ALL)],
            orphanRemoval = true
    )
    var players: MutableList<MatchPlayer> = arrayListOf()
}
