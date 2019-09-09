package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.dto.MapPoint
import com.dev.bruno.worms.domain.MatchPlayerStatus
import com.dev.bruno.worms.dto.Map
import com.dev.bruno.worms.dto.MapPlayer
import com.dev.bruno.worms.dto.RunningMatch
import kotlin.random.Random

class NewFoodLocationEvaluator : Evaluator() {

    override fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: Map?,
                              currentMap: Map) {
        val stillPlaying = currentMap.players.filter {
            it.status == MatchPlayerStatus.PLAYING
        }

        val playersLastPointIsEqualFoodPoint = stillPlaying
                .map { it.position.last() }
                .any { lastPoint -> lastPoint == currentMap.foodPosition }

        if (playersLastPointIsEqualFoodPoint) {
            currentMap.foodPosition = getFoodPosition(
                    runningMatch,
                    stillPlaying
            )
        }
    }

    private fun getFoodPosition(runningMatch: RunningMatch,
                                players: List<MapPlayer>): MapPoint {

        val allOccupiedPoints = players.flatMap { it.position }.toHashSet()

        var newFood: MapPoint
        while (true) {
            newFood = MapPoint(
                    Random.nextInt(0, runningMatch.mapSize - 1),
                    Random.nextInt(0, runningMatch.mapSize - 1)
            )
            if (!allOccupiedPoints.contains(newFood)) {
                break
            }
        }

        return newFood
    }
}
