package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.*
import com.dev.bruno.worms.dto.MatchInfo
import com.dev.bruno.worms.dto.NewMatch
import com.dev.bruno.worms.dto.NewMatchPlayer
import com.dev.bruno.worms.dto.NewRandomMatchPlayer
import com.dev.bruno.worms.exceptions.MatchNotFoundException
import com.dev.bruno.worms.exceptions.MatchRunningException
import com.dev.bruno.worms.exceptions.MaximumPlayersException
import com.dev.bruno.worms.exceptions.PlayerNotFoundException
import com.dev.bruno.worms.helpers.asMatch
import com.dev.bruno.worms.helpers.asMatchInfo
import com.dev.bruno.worms.helpers.asNewMatchPlayer
import com.dev.bruno.worms.repositories.MatchPlayerRepository
import com.dev.bruno.worms.repositories.MatchRepository
import com.dev.bruno.worms.repositories.PlayerRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class MatchService @Inject constructor(
    val playerRepository: PlayerRepository,
    val matchRepository: MatchRepository,
    val matchPlayerRepository: MatchPlayerRepository,
    val matchEvaluationService: MatchEvaluationService
) {

    fun addMatch(newMatch: NewMatch): MatchInfo {
        return matchRepository.save(newMatch.asMatch()).asMatchInfo()
    }

    fun addPlayerIntoMatch(
        matchId: Long,
        newMatchPlayer: NewMatchPlayer
    ): MatchInfo {
        val match = getMatch(matchId)
        return addPlayerIntoMatch(newMatchPlayer, match)
    }

    fun addPlayerIntoRandomMatch(newRandomMatchPlayer: NewRandomMatchPlayer): MatchInfo {
        var notStartedMatch = matchRepository.findNotStartedMatch(newRandomMatchPlayer.numberOfPlayers)
        if (notStartedMatch == null) {
            notStartedMatch = buildAndSaveNewMatch(newRandomMatchPlayer.numberOfPlayers)
        }
        return addPlayerIntoMatch(newRandomMatchPlayer.asNewMatchPlayer(), notStartedMatch)
    }

    private fun buildAndSaveNewMatch(numberOfPlayers: Int): Match {
        val match = Match(
                Wall.SOLID,
                OpponentBody.SOLID,
                Difficulty.HARD,
                PlayMode.LONGEST_WORM,
                numberOfPlayers,
                30
        )
        matchRepository.save(match)
        return match
    }

    private fun addPlayerIntoMatch(
        newMatchPlayer: NewMatchPlayer,
        match: Match
    ): MatchInfo {
        if (match.players.any { it.player.id == newMatchPlayer.playerId }) {
            return match.asMatchInfo()
        }
        if (match.numberOfPlayers == match.players.size) {
            throw MaximumPlayersException()
        }
        val player = playerRepository.get(newMatchPlayer.playerId)
        player ?: throw PlayerNotFoundException()
        val playerMatch = MatchPlayer(player, match)
        matchPlayerRepository.save(playerMatch)
        matchEvaluationService.startMatchIfItIsReady(match)
        return match.asMatchInfo()
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
