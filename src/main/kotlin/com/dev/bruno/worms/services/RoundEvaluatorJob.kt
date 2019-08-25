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

    private val initialWormSize = 2

    private val maps: MutableList<MatchMap> = arrayListOf()

    override fun execute(context: JobExecutionContext) {
        val dataMap = context.jobDetail.jobDataMap
        val runningMatch = dataMap.getString("match").fromJson(RunningMatch::class.java)
        val directions = getDirections(runningMatch)
        val food = evaluateFood(runningMatch)
        val players = evaluatePlayers(runningMatch, directions, food)

        val newMap = MatchMap(runningMatch.id, maps.size, players, food)
        maps.add(newMap)
        println(newMap.toJson())
    }

    private fun getDirections(runningMatch: RunningMatch): Map<Long?, Direction> {
        if (maps.isEmpty()) {
            return runningMatch.players.mapIndexed { index, id ->
                id to Direction.values()[index / runningMatch.players.size]
            }.toMap()
        }
        val previousDirections = maps.last().players.map {
            it.playerId to it.direction
        }.toMap()
        val newDirections = PlayerActionPool.playerActions.filter {
            runningMatch.players.contains(it.key)
        }.toMap()
        return previousDirections + newDirections
    }

    private fun evaluatePlayers(runningMatch: RunningMatch, directions: Map<Long?, Direction>, food: MapPoint): Set<MatchMapPlayer> {
        if (maps.isEmpty()) {
            return directions.map {
                MatchMapPlayer(it.key, PlayerStatus.PLAYING, initialWormSize, it.value, calculateInitialPosition(it.value, runningMatch.mapSize))
            }.toSet()
        }
        return maps.last().players.map {
            var newDirection = directions.getValue(it.playerId)
            if (newDirection.isOpposite(it.direction)) {
                newDirection = it.direction
            }

            val newLastPoint = calculateNewLastPoint(it.position.last(), newDirection)

            val startIndex = if (newLastPoint == food) 0 else 1

            val newPosition = it.position.subList(startIndex, it.position.size) + newLastPoint

            val newStatus = if (newPosition.last().x < 0 || newPosition.last().x >= runningMatch.mapSize || newPosition.last().y < 0 || newPosition.last().y >= runningMatch.mapSize) PlayerStatus.DEAD else PlayerStatus.PLAYING

            MatchMapPlayer(it.playerId, newStatus, initialWormSize, newDirection, newPosition)
        }.toSet()
    }

    private fun calculateNewLastPoint(lastPoint: MapPoint, direction: Direction): MapPoint {
        return when (direction) {
            Direction.UP -> MapPoint(lastPoint.x, lastPoint.y + 1)
            Direction.RIGHT -> MapPoint(lastPoint.x + 1, lastPoint.y)
            Direction.DOWN -> MapPoint(lastPoint.x, lastPoint.y - 1)
            Direction.LEFT -> MapPoint(lastPoint.x - 1, lastPoint.y)
        }
    }

    private fun calculateInitialPosition(direction: Direction, mapSize: Int): List<MapPoint> {
        return when (direction) {
            Direction.UP -> arrayListOf(MapPoint(0, 0), MapPoint(0, 1))
            Direction.RIGHT -> arrayListOf(MapPoint(0, mapSize - 1), MapPoint(1, mapSize - 1))
            Direction.DOWN -> arrayListOf(MapPoint(mapSize - 1, mapSize - 1), MapPoint(mapSize - 1, mapSize - 2))
            Direction.LEFT -> arrayListOf(MapPoint(mapSize - 1, 0), MapPoint(mapSize - 2, 0))
        }
    }

    private fun evaluateFood(runningMatch: RunningMatch): MapPoint {
        return MapPoint(0, 0)
    }
}