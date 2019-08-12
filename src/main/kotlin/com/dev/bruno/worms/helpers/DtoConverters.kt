package com.dev.bruno.worms.helpers

import com.dev.bruno.worms.domain.Player
import com.dev.bruno.worms.dto.NewPlayer
import com.dev.bruno.worms.dto.PlayerInfo

fun NewPlayer.asPlayer() = Player(this.nickname)

fun Player.asPlayerInfo() = PlayerInfo(this.id, this.nickname)
