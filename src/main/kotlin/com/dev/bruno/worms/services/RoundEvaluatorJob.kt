package com.dev.bruno.worms.services

import org.quartz.Job
import org.quartz.JobExecutionContext

class RoundEvaluatorJob : Job {

    override fun execute(context: JobExecutionContext) {
        val dataMap = context.jobDetail.jobDataMap
        val matchId = dataMap.getLong("matchId")
        println("This is a round evaluator job for match[$matchId]!")
    }
}