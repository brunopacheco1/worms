package com.dev.bruno.worms.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Player(val nickname: String) {

    @Id
    lateinit var id: String

    @OneToMany(mappedBy = "player")
    val matches: Set<PlayerMatch> = hashSetOf()
}