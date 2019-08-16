package com.dev.bruno.worms.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Player(val nickname: String) {

    @Id
    @GeneratedValue
    var id: Long = 0

    @OneToMany(mappedBy = "player")
    val matches: Set<PlayerMatch> = hashSetOf()
}