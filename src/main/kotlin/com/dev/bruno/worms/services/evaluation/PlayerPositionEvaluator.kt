package com.dev.bruno.worms.services.evaluation

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.domain.MapPoint
import com.dev.bruno.worms.domain.PlayerStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch

class PlayerPositionEvaluator : Evaluator() {

    override fun evaluate(runningMatch: RunningMatch, lastMap: MatchMap?, currentMap: MatchMap) {
        if (lastMap != null) {
            currentMap.players
                    .filter { it.status == PlayerStatus.PLAYING }
                    .forEachIndexed { index, playerCurrentState ->
                        val playerLastState = lastMap.players[index]
                        val newLastPoint = calculateNewLastPoint(playerLastState.position.last(), playerCurrentState.direction)
                        val startIndex = if (newLastPoint == currentMap.foodPosition) 0 else 1
                        playerCurrentState.position = playerLastState.position.subList(startIndex, playerLastState.position.size) + newLastPoint
                        playerCurrentState.wormLength = playerCurrentState.position.size
                    }
        }
        next?.evaluate(runningMatch, lastMap, currentMap)
    }

    private fun calculateNewLastPoint(lastPoint: MapPoint, direction: Direction): MapPoint {
        return when (direction) {
            Direction.UP -> MapPoint(lastPoint.x, lastPoint.y + 1)
            Direction.RIGHT -> MapPoint(lastPoint.x + 1, lastPoint.y)
            Direction.DOWN -> MapPoint(lastPoint.x, lastPoint.y - 1)
            Direction.LEFT -> MapPoint(lastPoint.x - 1, lastPoint.y)
        }
    }
}