package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.MapPoint
import com.dev.bruno.worms.domain.MatchStatus
import com.dev.bruno.worms.domain.PlayerStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.MatchMapPlayer
import com.dev.bruno.worms.dto.RunningMatch
import com.dev.bruno.worms.helpers.fromJson
import com.dev.bruno.worms.helpers.toJson
import com.dev.bruno.worms.services.evaluation.RoundEvaluatorFactory
import org.quartz.*

class RoundEvaluatorJob : Job {

    override fun execute(context: JobExecutionContext) {
        val dataMap = context.jobDetail.jobDataMap
        val runningMatch = dataMap.getString("match").fromJson(RunningMatch::class.java)
        val currentMap = buildNewMap(runningMatch)
        val maps = PlayerMatchPool.playerMatches.getValue(runningMatch.id)
        val lastMap = maps.lastOrNull()
        val evaluator = RoundEvaluatorFactory.getRoundEvaluator()
        evaluator.evaluate(runningMatch, lastMap, currentMap)

        if(lastMap == null || lastMap.status != MatchStatus.FINISHED) {
            maps.add(currentMap)
            if (currentMap.status == MatchStatus.FINISHED) {
                context.scheduler.rescheduleJob(context.trigger.key, buildStoppingTrigger())
            }
        }
        println(currentMap.toJson())
    }

    private fun buildNewMap(runningMatch: RunningMatch): MatchMap {
        return MatchMap(
                runningMatch.id,
                0,
                runningMatch.players.map { MatchMapPlayer(it) }.toMutableList(),
                MapPoint(0, 0)
        )
    }

    private fun buildStoppingTrigger(): SimpleTrigger {
        return TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withRepeatCount(0))
                .build()
    }
}