package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.dto.MapPoint
import com.dev.bruno.worms.dto.Map
import com.dev.bruno.worms.dto.RunningMatch

class BasicInitializerEvaluator : Evaluator() {

    private val initialWormSize = 2

    override fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: Map?,
                              currentMap: Map) {
        if (lastMap == null) {
            currentMap.foodPosition = MapPoint(
                    runningMatch.mapSize / 2, runningMatch.mapSize / 2
            )
            currentMap.roundCounter = 1
            currentMap.players
                    .forEachIndexed { index, player ->
                        player.direction = calculateInitialDirection(
                                index, runningMatch.players.size
                        )
                        player.position = calculateInitialPosition(
                                player.direction, runningMatch.mapSize
                        )
                        player.wormLength = initialWormSize
                    }
        } else {
            currentMap.foodPosition = lastMap.foodPosition
            currentMap.roundCounter = lastMap.roundCounter + 1
            lastMap.players.forEachIndexed { index, player ->
                val currentPlayer = currentMap.players[index]
                currentPlayer.status = player.status
                currentPlayer.position = player.position
                currentPlayer.wormLength = player.wormLength
                currentPlayer.direction = player.direction
            }
        }
    }

    private fun calculateInitialDirection(index: Int,
                                           size: Int): Direction {
        return Direction.values()[index % size]
    }

    private fun calculateInitialPosition(dir: Direction,
                                         size: Int): List<MapPoint> {
        return when (dir) {
            Direction.UP -> arrayListOf(
                    MapPoint(0, 0), MapPoint(0, 1)
            )
            Direction.RIGHT -> arrayListOf(
                    MapPoint(0, size - 1), MapPoint(1, size - 1)
            )
            Direction.DOWN -> arrayListOf(
                    MapPoint(size - 1, size - 1), MapPoint(size - 1, size - 2)
            )
            Direction.LEFT -> arrayListOf(
                    MapPoint(size - 1, 0), MapPoint(size - 2, 0)
            )
        }
    }
}
