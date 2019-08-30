package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.Match
import com.dev.bruno.worms.domain.MatchStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.PlayerAction
import com.dev.bruno.worms.exceptions.MatchFinishedException
import com.dev.bruno.worms.exceptions.MatchNotFoundException
import com.dev.bruno.worms.exceptions.MatchNotStartedException
import com.dev.bruno.worms.exceptions.PlayerNotFoundException
import com.dev.bruno.worms.repositories.MatchRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject


@ApplicationScoped
class RoundService @Inject constructor(
        val matchRepository: MatchRepository,
        val schedulerService: SchedulerService,
        val poolService: PlayerMatchPoolService
) {

    fun addAction(matchId: Long, playerAction: PlayerAction) {
        validateMatch(matchId, playerAction.playerId)
        poolService.addPlayerAction(playerAction)
    }

    private fun validateMatch(matchId: Long, playerId: Long) {
        val match = matchRepository.get(matchId)
        if (match == null) {
            throw MatchNotFoundException()
        }
        if (match.status == MatchStatus.WAITING_PLAYERS) {
            throw MatchNotStartedException()
        }
        if (match.status == MatchStatus.FINISHED) {
            throw MatchFinishedException()
        }
        if (!hasPlayer(match, playerId)) {
            throw PlayerNotFoundException()
        }
    }

    private fun hasPlayer(match: Match, playerId: Long): Boolean {
        return match.players.any { it.player.id == playerId }
    }

    fun startMatchIfItIsReady(match: Match) {
        if (match.numberOfPlayers == match.players.size) {
            updateMatchStatus(match)
            schedulerService.startMatch(match)
        }
    }

    private fun updateMatchStatus(match: Match) {
        match.status = MatchStatus.RUNNING
        matchRepository.update(match)
    }

    fun generateMap(matchId: Long): MatchMap {
        val match = matchRepository.get(matchId)
        if(match == null) {
            throw MatchNotFoundException()
        }
        if (match.status != MatchStatus.RUNNING) {
            throw MatchNotStartedException()
        }
        return poolService.getLastMap(matchId)
    }
}
