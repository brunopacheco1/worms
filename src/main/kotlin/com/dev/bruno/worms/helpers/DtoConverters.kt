package com.dev.bruno.worms.helpers

import com.dev.bruno.worms.domain.Match
import com.dev.bruno.worms.domain.Player
import com.dev.bruno.worms.domain.PlayerRound
import com.dev.bruno.worms.dto.*

fun NewPlayer.asPlayer() = Player(nickname)

fun Player.asPlayerInfo() = PlayerInfo(id, nickname)

fun NewMatch.asMatch() = Match(gameMode, gamePlay, difficulty, playerMode, numberOfPlayers, mapSize)

fun Match.asMatchInfo() = MatchInfo(id, status, gameMode, gamePlay, difficulty, playerMode, numberOfPlayers, mapSize, players.map { it.player.asPlayerInfo() })

fun PlayerRound.asMatchMapPlayer() = MatchMapPlayer(player.player.id, player.status, player.wormLength, direction, currentPosition)

fun Match.asMatchMap() = MatchMap(id, rounds.size, rounds.last().players.map { it.asMatchMapPlayer() }.toSet(), rounds.last().foodPosition)