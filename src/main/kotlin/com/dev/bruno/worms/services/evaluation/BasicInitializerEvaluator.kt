package com.dev.bruno.worms.services.evaluation

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.domain.MapPoint
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch

class BasicInitializerEvaluator : Evaluator() {

    private val initialWormSize = 2

    override fun evaluate(runningMatch: RunningMatch, lastMap: MatchMap?, currentMap: MatchMap) {
        if (lastMap == null) {
            currentMap.foodPosition = MapPoint(runningMatch.mapSize / 2, runningMatch.mapSize / 2)
            currentMap.roundCounter = 1
            currentMap.players.forEachIndexed { index, player ->
                player.direction = Direction.values()[index % runningMatch.players.size]
                player.position = calculateInitialPosition(player.direction, runningMatch.mapSize)
                player.wormLength = initialWormSize
            }
        } else {
            currentMap.foodPosition = lastMap.foodPosition
            currentMap.roundCounter = lastMap.roundCounter + 1
            lastMap.players.forEachIndexed { index, player ->
                currentMap.players[index].status = player.status
                currentMap.players[index].position = player.position
                currentMap.players[index].wormLength = player.wormLength
                currentMap.players[index].direction = player.direction
            }
        }
        next?.evaluate(runningMatch, lastMap, currentMap)
    }

    private fun calculateInitialPosition(direction: Direction, mapSize: Int): List<MapPoint> {
        return when (direction) {
            Direction.UP -> arrayListOf(MapPoint(0, 0), MapPoint(0, 1))
            Direction.RIGHT -> arrayListOf(MapPoint(0, mapSize - 1), MapPoint(1, mapSize - 1))
            Direction.DOWN -> arrayListOf(MapPoint(mapSize - 1, mapSize - 1), MapPoint(mapSize - 1, mapSize - 2))
            Direction.LEFT -> arrayListOf(MapPoint(mapSize - 1, 0), MapPoint(mapSize - 2, 0))
        }
    }
}