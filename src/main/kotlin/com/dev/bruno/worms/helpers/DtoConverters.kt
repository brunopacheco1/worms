package com.dev.bruno.worms.helpers

import com.dev.bruno.worms.domain.MapPoint
import com.dev.bruno.worms.domain.Match
import com.dev.bruno.worms.domain.Player
import com.dev.bruno.worms.dto.*

fun NewPlayer.asPlayer() = Player(nickname)

fun Player.asPlayerInfo() = PlayerInfo(id, nickname)

fun NewMatch.asMatch() = Match(wall, opponentBody, difficulty, playMode, numberOfPlayers, mapSize)

fun Match.asMatchInfo() = MatchInfo(id, status, wall, opponentBody, difficulty, playMode, numberOfPlayers, mapSize, players.map { it.player.asPlayerInfo() })

fun Match.asRunningMatch() = RunningMatch(id, wall, opponentBody, difficulty, playMode, mapSize, players.map { it.player.id }.toList())

fun RunningMatch.asMatchMap() = MatchMap(id, 0, mapSize, players.map { MatchMapPlayer(it) }.toMutableList(), MapPoint(0, 0))
