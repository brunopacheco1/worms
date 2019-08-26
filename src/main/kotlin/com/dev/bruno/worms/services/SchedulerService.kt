package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.Match
import com.dev.bruno.worms.helpers.asRunningMatch
import com.dev.bruno.worms.helpers.toJson
import io.quarkus.runtime.StartupEvent
import org.quartz.*
import org.quartz.impl.StdSchedulerFactory
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes


@ApplicationScoped
class SchedulerService {

    val scheduler: Scheduler = StdSchedulerFactory().scheduler

    fun onStart(@Observes ev: StartupEvent) {
        scheduler.start()
    }

    fun startMatch(match: Match) {
        val job = buildJob(match)
        val trigger = buildTrigger(match)
        scheduler.scheduleJob(job, trigger)
    }

    private fun buildTrigger(match: Match): SimpleTrigger {
        return TriggerBuilder.newTrigger()
                .withIdentity("trigger-${match.id}", "group-${match.id}")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMilliseconds(match.difficulty.tickRate)
                        .repeatForever())
                .build()
    }

    private fun buildJob(match: Match): JobDetail {
        return JobBuilder.newJob(RoundEvaluatorJob::class.java)
                .withIdentity("round-evaluator-${match.id}", "round-evaluator")
                .usingJobData("match", match.asRunningMatch().toJson())
                .build()
    }
}