package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.PlayerMatch
import com.dev.bruno.worms.dto.MatchInfo
import com.dev.bruno.worms.dto.NewMatch
import com.dev.bruno.worms.dto.NewMatchPlayer
import com.dev.bruno.worms.helpers.asMatch
import com.dev.bruno.worms.helpers.asMatchInfo
import com.dev.bruno.worms.repositories.MatchRepository
import com.dev.bruno.worms.repositories.PlayerRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class MatchService {

    @Inject
    lateinit var matchRepository: MatchRepository

    @Inject
    lateinit var playerRepository: PlayerRepository

    fun add(newMatch: NewMatch) = matchRepository.save(newMatch.asMatch()).asMatchInfo()

    fun addPlayerIntoMatch(matchId: Long, newMatchPlayer: NewMatchPlayer): MatchInfo {
        val match = matchRepository.get(matchId)
        val player = playerRepository.get(newMatchPlayer.playerId)
        match.players.add(PlayerMatch(player, match))
        return matchRepository.update(match).asMatchInfo()
    }

    fun getMatch(id: Long) = matchRepository.get(id).asMatchInfo()

    fun listMatches() = matchRepository.list().map { it.asMatchInfo() }
}