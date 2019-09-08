package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.Match
import com.dev.bruno.worms.domain.MatchStatus
import com.dev.bruno.worms.dto.PlayerAction
import com.dev.bruno.worms.exceptions.MatchFinishedException
import com.dev.bruno.worms.exceptions.MatchNotFoundException
import com.dev.bruno.worms.exceptions.MatchNotStartedException
import com.dev.bruno.worms.exceptions.PlayerNotFoundException
import com.dev.bruno.worms.repositories.MatchRepository
import org.reactivestreams.Publisher
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject


@ApplicationScoped
class RoundService @Inject constructor(
        val matchRepository: MatchRepository,
        val schedulerService: SchedulerService
) {

    fun addAction(matchId: Long, playerAction: PlayerAction) {
        validateMatchAndPlayer(matchId, playerAction.playerId)
        MatchPool.addPlayerAction(playerAction)
    }

    private fun validateMatch(match: Match?) {
        match ?: throw MatchNotFoundException()
        if (match.status == MatchStatus.WAITING_PLAYERS) {
            throw MatchNotStartedException()
        }
        if (match.status == MatchStatus.FINISHED) {
            throw MatchFinishedException()
        }
    }

    private fun validateMatchAndPlayer(matchId: Long, playerId: Long) {
        val match = matchRepository.get(matchId)
        validateMatch(match)
        if (!hasPlayer(match, playerId)) {
            throw PlayerNotFoundException()
        }
    }

    private fun hasPlayer(match: Match?, playerId: Long): Boolean {
        return match!!.players.any { it.player.id == playerId }
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

    fun streamingMatchMap(matchId: Long): Publisher<String> {
        val match = matchRepository.get(matchId)
        validateMatch(match)
        return schedulerService.streamingMatchMap(matchId)
    }
}
