package com.dev.bruno.worms.domain

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity(name = "PLAYER")
data class Player(
        @Column(name = "nickname")
        var nickname: String
) : Persistable<Long>() {

    @OneToMany(
            mappedBy = "player",
            cascade = [(CascadeType.ALL)],
            orphanRemoval = true
    )
    var matchPlayers: MutableSet<MatchPlayer> = hashSetOf()
}
