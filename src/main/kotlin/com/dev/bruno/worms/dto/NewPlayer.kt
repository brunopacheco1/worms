package com.dev.bruno.worms.dto

class NewPlayer {
    lateinit var nickname: String

    constructor(nickname: String) {
        this.nickname = nickname
    }

    constructor()
}
