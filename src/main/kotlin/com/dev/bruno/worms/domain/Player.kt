package com.dev.bruno.worms.domain

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
data class Player(var nickname: String) : Persistable<Long>() {
    @OneToMany(mappedBy = "player", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    var matches: MutableSet<PlayerMatch> = hashSetOf()
}