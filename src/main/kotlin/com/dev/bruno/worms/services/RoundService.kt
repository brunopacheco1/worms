package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.*
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.PlayerAction
import com.dev.bruno.worms.exceptions.MatchFinishedException
import com.dev.bruno.worms.exceptions.MatchNotFoundException
import com.dev.bruno.worms.exceptions.MatchNotStartedException
import com.dev.bruno.worms.exceptions.PlayerNotFoundException
import com.dev.bruno.worms.helpers.asMatchMap
import com.dev.bruno.worms.repositories.MatchRepository
import com.dev.bruno.worms.repositories.PlayerRoundRepository
import com.dev.bruno.worms.repositories.RoundRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import kotlin.math.round


@ApplicationScoped
class RoundService @Inject constructor(
        val matchRepository: MatchRepository,
        val roundRepository: RoundRepository,
        val playerRoundRepository: PlayerRoundRepository,
        val schedulerService: SchedulerService
) {

    fun addAction(matchId: Long, playerAction: PlayerAction) {
        val match = getMatch(matchId)
        val playerRound = getPlayerRound(match, playerAction)
        playerRound.direction = playerAction.direction
        playerRoundRepository.update(playerRound)
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

    fun startMatchIfItIsReady(match: Match) {
        if (match.numberOfPlayers == match.players.size) {
            updateMatchStatus(match)
            buildFirstRound(match)
            schedulerService.startMatch(match)
        }
    }

    private fun updateMatchStatus(match: Match) {
        match.status = MatchStatus.RUNNING
        matchRepository.update(match)
    }

    private fun buildFirstRound(match: Match) {
        val round = initializeRound(match)
        savePlayersRound(match, round)
    }

    private fun initializeRound(match: Match): Round {
        val round = Round(0, match, initialFoodPosition(match))
        roundRepository.save(round)
        return round
    }

    private fun initialFoodPosition(match: Match): MapPoint {
        val foodPosition = match.mapSize / 2
        return MapPoint(foodPosition, foodPosition)
    }

    private fun savePlayersRound(match: Match, round: Round) {
        val players = match.players.map { PlayerRound(it, round) }
        players.forEach {
            it.direction = Direction.DOWN
            it.currentPosition = arrayListOf(MapPoint(0, 0))
        }
        players.forEach { playerRoundRepository.save(it) }
    }

    fun generateMap(matchId: Long): MatchMap {
        val match = matchRepository.get(matchId) ?: throw MatchNotFoundException()
        if (match.status != MatchStatus.RUNNING) throw MatchNotStartedException()
        return match.asMatchMap()
    }
}