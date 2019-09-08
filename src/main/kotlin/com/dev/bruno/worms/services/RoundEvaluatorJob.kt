package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.MatchStatus
import com.dev.bruno.worms.dto.RunningMatch
import com.dev.bruno.worms.helpers.toJson
import com.dev.bruno.worms.services.evaluation.RoundEvaluatorFactory
import io.vertx.axle.core.Vertx
import java.util.function.Consumer

class RoundEvaluatorJob(
        val match: RunningMatch,
        val vertx: Vertx
) : Consumer<Long> {

    override fun accept(timerId: Long) {
        val evaluator = RoundEvaluatorFactory.getRoundEvaluator(match)
        val currentMap = evaluator.evaluate(match)
        vertx.eventBus().publish("match-${match.id}", currentMap.toJson())
        if (currentMap.status == MatchStatus.FINISHED) {
            vertx.cancelTimer(timerId)
        }
    }
}
