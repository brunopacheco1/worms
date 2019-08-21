package com.dev.bruno.worms.domain

import javax.persistence.*

@Entity
data class Player(val nickname: String,
                  @Id
                  @GeneratedValue(strategy = GenerationType.IDENTITY)
                  val id: Long = 0
) {
    @OneToMany(mappedBy = "player")
    val matches: Set<PlayerMatch> = hashSetOf()
}