package com.dev.bruno.worms.domain

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
data class Player(val nickname: String) : Persistable<Long>() {
    @OneToMany(mappedBy = "player", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    val matches: Set<PlayerMatch> = hashSetOf()
}