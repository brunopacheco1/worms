package com.dev.bruno.worms.helpers

import com.dev.bruno.worms.domain.Match
import com.dev.bruno.worms.domain.Player
import com.dev.bruno.worms.dto.MatchInfo
import com.dev.bruno.worms.dto.NewMatch
import com.dev.bruno.worms.dto.NewPlayer
import com.dev.bruno.worms.dto.PlayerInfo

fun NewPlayer.asPlayer() = Player(this.nickname)

fun Player.asPlayerInfo() = PlayerInfo(this.id, this.nickname)

fun NewMatch.asMatch() = Match(gameMode, gamePlay, difficulty, playerMode, numberOfPlayers, mapSize)

fun Match.asMatchInfo() = MatchInfo(id, gameMode, gamePlay, difficulty, playerMode, numberOfPlayers, mapSize, players.map { it.player.id })