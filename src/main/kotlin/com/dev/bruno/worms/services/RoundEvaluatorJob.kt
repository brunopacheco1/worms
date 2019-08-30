package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.MatchStatus
import com.dev.bruno.worms.dto.RunningMatch
import com.dev.bruno.worms.helpers.fromJson
import com.dev.bruno.worms.services.evaluation.RoundEvaluatorFactory
import org.quartz.*

class RoundEvaluatorJob : Job {

    override fun execute(context: JobExecutionContext) {
        val dataMap = context.jobDetail.jobDataMap
        val matchStr = dataMap.getString("match")
        val match = matchStr.fromJson(RunningMatch::class.java)
        val evaluator = RoundEvaluatorFactory.getRoundEvaluator()
        val currentMap = evaluator.evaluate(match)

        if (currentMap.status == MatchStatus.FINISHED) {
            context.scheduler.rescheduleJob(
                    context.trigger.key, buildStoppingTrigger()
            )
        }
    }

    private fun buildStoppingTrigger(): SimpleTrigger {
        return TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withRepeatCount(0)
                )
                .build()
    }
}
