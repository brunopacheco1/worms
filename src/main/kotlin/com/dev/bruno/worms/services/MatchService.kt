package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.Match
import com.dev.bruno.worms.domain.MatchStatus
import com.dev.bruno.worms.domain.PlayerMatch
import com.dev.bruno.worms.dto.MatchInfo
import com.dev.bruno.worms.dto.NewMatch
import com.dev.bruno.worms.dto.NewMatchPlayer
import com.dev.bruno.worms.exceptions.MatchNotFoundException
import com.dev.bruno.worms.exceptions.MatchRunningException
import com.dev.bruno.worms.exceptions.MaximumPlayersException
import com.dev.bruno.worms.exceptions.PlayerNotFoundException
import com.dev.bruno.worms.helpers.asMatch
import com.dev.bruno.worms.helpers.asMatchInfo
import com.dev.bruno.worms.repositories.MatchRepository
import com.dev.bruno.worms.repositories.PlayerMatchRepository
import com.dev.bruno.worms.repositories.PlayerRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class MatchService @Inject constructor(
        val playerRepository: PlayerRepository,
        val matchRepository: MatchRepository,
        val playerMatchRepository: PlayerMatchRepository,
        val roundService: RoundService
) {

    fun addMatch(newMatch: NewMatch): MatchInfo {
        return matchRepository.save(newMatch.asMatch()).asMatchInfo()
    }

    fun addPlayerIntoMatch(matchId: Long,
                           newMatchPlayer: NewMatchPlayer): MatchInfo {
        val match = getMatch(matchId)
        if (match.players.any { it.player.id == newMatchPlayer.playerId }) {
            return match.asMatchInfo()
        }
        addPlayerIntoMatch(newMatchPlayer, match)
        roundService.startMatchIfItIsReady(match)
        return match.asMatchInfo()
    }

    private fun addPlayerIntoMatch(newMatchPlayer: NewMatchPlayer,
                                   match: Match) {
        if (match.numberOfPlayers == match.players.size) {
            throw MaximumPlayersException()
        }
        val player = playerRepository.get(newMatchPlayer.playerId)
        player ?: throw PlayerNotFoundException()
        val playerMatch = PlayerMatch(player, match)
        playerMatchRepository.save(playerMatch)
    }

    private fun getMatch(id: Long): Match {
        val match = matchRepository.get(id)
        match ?: throw MatchNotFoundException()
        if (match.status == MatchStatus.RUNNING) {
            throw MatchRunningException()
        }
        return match
    }

    fun retrieveMatch(id: Long): MatchInfo {
        val match = matchRepository.get(id)
        match ?: throw MatchNotFoundException()
        return match.asMatchInfo()
    }

    fun retrieveMatches(): List<MatchInfo> {
        return matchRepository.list().map { it.asMatchInfo() }
    }
}
