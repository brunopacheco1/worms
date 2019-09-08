package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.Match
import com.dev.bruno.worms.helpers.asRunningMatch
import io.vertx.axle.core.Vertx
import org.reactivestreams.Publisher
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject


@ApplicationScoped
class SchedulerService @Inject constructor(
        val vertx: Vertx
) {

    fun startMatch(match: Match) {
        val runningMatch = match.asRunningMatch()
        val job = RoundEvaluatorJob(runningMatch, vertx)
        vertx.setPeriodic(match.difficulty.tickRate, job)
    }

    fun streamingMatchMap(matchId: Long): Publisher<String> {
        return vertx.eventBus().consumer<String>("match-$matchId").bodyStream().toPublisher()
    }
}
