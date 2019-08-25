package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.domain.MapPoint
import com.dev.bruno.worms.domain.PlayerStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.MatchMapPlayer
import com.dev.bruno.worms.dto.RunningMatch
import com.dev.bruno.worms.helpers.fromJson
import com.dev.bruno.worms.helpers.toJson
import org.quartz.Job
import org.quartz.JobExecutionContext

class RoundEvaluatorJob : Job {

    private val initialWormSize = 2;

    private val maps: MutableList<MatchMap> = arrayListOf()

    override fun execute(context: JobExecutionContext) {
        val dataMap = context.jobDetail.jobDataMap
        val runningMatch = dataMap.getString("match").fromJson(RunningMatch::class.java)
        val directions = getDirections(runningMatch)
        val players = evaluatePlayers(runningMatch, directions)
        val food = evaluateFood(runningMatch)
        val newMap = MatchMap(runningMatch.id, maps.size, players, food)
        maps.add(newMap)
        println(newMap.toJson())
    }

    private fun getDirections(runningMatch: RunningMatch): Map<Long, Direction> {
        if (maps.isEmpty()) {
            return runningMatch.players.map { it to Direction.DOWN }.toMap()
        }
        return PlayerActionPool.playerActions.filter { runningMatch.players.contains(it.key) }
    }

    private fun evaluatePlayers(runningMatch: RunningMatch, directions: Map<Long, Direction>): Set<MatchMapPlayer> {
        if (maps.isEmpty()) {
            directions.map { MatchMapPlayer(it.key, PlayerStatus.PLAYING, initialWormSize, it.value, calculateInitialPosition()) }
        }
        return emptySet()
    }

    private fun calculateInitialPosition(): List<MapPoint> {
        return arrayListOf()
    }

    private fun evaluateFood(runningMatch: RunningMatch): MapPoint {
        return MapPoint(0, 0)
    }
}