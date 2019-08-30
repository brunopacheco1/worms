package com.dev.bruno.worms.services.evaluation

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.domain.MapPoint
import com.dev.bruno.worms.domain.PlayerStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch

class PlayerPositionEvaluator : Evaluator() {

    override fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: MatchMap?,
                              currentMap: MatchMap) {

        if (lastMap != null) {
            val stillPlaying = currentMap.players.filter {
                it.status == PlayerStatus.PLAYING
            }

            stillPlaying.forEachIndexed { index, currentState ->
                val lastState = lastMap.players[index]

                val newPoint = getNewLastPoint(
                    lastState.position.last(),
                    currentState.direction
                )

                var startIndex = 1
                if (newPoint == currentMap.foodPosition) {
                    startIndex = 0
                }

                currentState.position = lastState.position
                     .subList(startIndex, lastState.position.size) +
                     newPoint

                currentState.wormLength = currentState.position.size
            }
        }
    }

    private fun getNewLastPoint(lastPoint: MapPoint,
                                direction: Direction): MapPoint {

        return when (direction) {
            Direction.UP -> MapPoint(lastPoint.x, lastPoint.y + 1)
            Direction.RIGHT -> MapPoint(lastPoint.x + 1, lastPoint.y)
            Direction.DOWN -> MapPoint(lastPoint.x, lastPoint.y - 1)
            Direction.LEFT -> MapPoint(lastPoint.x - 1, lastPoint.y)
        }
    }
}
