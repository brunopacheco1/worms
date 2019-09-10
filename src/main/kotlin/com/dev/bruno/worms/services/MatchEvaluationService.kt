package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.Match
import com.dev.bruno.worms.domain.MatchStatus
import com.dev.bruno.worms.dto.PlayerAction
import com.dev.bruno.worms.dto.RunningMatch
import com.dev.bruno.worms.evaluation.MatchEvaluatorFactory
import com.dev.bruno.worms.exceptions.MatchFinishedException
import com.dev.bruno.worms.exceptions.MatchNotFoundException
import com.dev.bruno.worms.exceptions.MatchNotStartedException
import com.dev.bruno.worms.exceptions.PlayerNotFoundException
import com.dev.bruno.worms.helpers.asRunningMatch
import com.dev.bruno.worms.helpers.fromJson
import com.dev.bruno.worms.helpers.toJson
import com.dev.bruno.worms.repositories.MatchPlayerRepository
import com.dev.bruno.worms.repositories.MatchRepository
import io.quarkus.vertx.ConsumeEvent
import io.vertx.axle.core.Vertx
import io.vertx.core.eventbus.Message
import org.reactivestreams.Publisher
import java.util.function.Consumer
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional


@ApplicationScoped
class MatchEvaluationService @Inject constructor(
        val matchRepository: MatchRepository,
        val matchPlayerRepository: MatchPlayerRepository,
        val vertx: Vertx
) {

    fun addAction(matchId: Long, playerAction: PlayerAction) {
        validateMatchAndPlayer(matchId, playerAction.playerId)
        MatchPool.addAction(playerAction)
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
            startEvaluation(match)
        }
    }

    private fun updateMatchStatus(match: Match) {
        match.status = MatchStatus.RUNNING
        matchRepository.update(match)
    }

    private fun startEvaluation(match: Match) {
        val runningMatch = match.asRunningMatch()
        val periodic = buildPeriodic(runningMatch)
        vertx.setPeriodic(match.difficulty.tickRate, periodic)
    }

    private fun buildPeriodic(match: RunningMatch): Consumer<Long> {
        return Consumer { periodicId ->
            val evaluator = MatchEvaluatorFactory.getEvaluator(match)
            val currentMap = evaluator.evaluate(match)
            MatchPool.setLastMap(currentMap)
            println(currentMap.toJson())
            vertx.eventBus().publish("match-${match.id}", currentMap.toJson())
            if (currentMap.status == MatchStatus.FINISHED) {
                vertx.eventBus().send<String>("finishing-match", match.toJson())
                vertx.cancelTimer(periodicId)
            }
        }
    }

    fun streamingMatchMap(matchId: Long): Publisher<String> {
        val match = matchRepository.get(matchId)
        validateMatch(match)
        return vertx.eventBus().consumer<String>("match-$matchId").bodyStream().toPublisher()
    }

    @ConsumeEvent("finishing-match")
    @Transactional
    fun finishingMatch(msg: Message<String>) {
        val runningMatch = msg.body().fromJson(RunningMatch::class.java)
        val lastMap = MatchPool.getLastMap(runningMatch.id)
        val match = matchRepository.get(runningMatch.id)

        if (match != null && lastMap != null) {
            match.status = lastMap.status
            matchRepository.update(match)
            match.players.forEachIndexed { index, playerMatch ->
                playerMatch.status = lastMap.players[index].status
                playerMatch.wormLength = lastMap.players[index].wormLength
                matchPlayerRepository.update(playerMatch)
            }
        }

        MatchPool.clearMapAndAction(runningMatch)
    }
}
