package com.dev.bruno.worms.domain

import javax.persistence.*

@Entity
class Player(val nickname: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @OneToMany(mappedBy = "player")
    val matches: Set<PlayerMatch> = hashSetOf()
}