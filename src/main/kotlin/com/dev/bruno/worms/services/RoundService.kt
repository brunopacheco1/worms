package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.Match
import com.dev.bruno.worms.domain.MatchStatus
import com.dev.bruno.worms.domain.PlayerRound
import com.dev.bruno.worms.dto.PlayerAction
import com.dev.bruno.worms.exceptions.MatchFinishedException
import com.dev.bruno.worms.exceptions.MatchNotFoundException
import com.dev.bruno.worms.exceptions.MatchNotStartedException
import com.dev.bruno.worms.exceptions.PlayerNotFoundException
import com.dev.bruno.worms.repositories.MatchRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class RoundService {

    @Inject
    lateinit var matchRepository: MatchRepository

    fun addAction(matchId: Long, playerAction: PlayerAction) {
        val match = getMatch(matchId)
        val playerRound = getPlayerRound(match, playerAction)
        playerRound.direction = playerAction.direction
    }

    private fun getMatch(matchId: Long): Match {
        val match = matchRepository.get(matchId) ?: throw MatchNotFoundException()
        if (match.status == MatchStatus.FINISHED) throw MatchFinishedException()
        return match
    }

    private fun getPlayerRound(match: Match, playerAction: PlayerAction): PlayerRound {
        val currentRound = match.rounds.lastOrNull() ?: throw MatchNotStartedException()
        return currentRound.players.find { it.player.player.id == playerAction.playerId }
                ?: throw PlayerNotFoundException()
    }
}