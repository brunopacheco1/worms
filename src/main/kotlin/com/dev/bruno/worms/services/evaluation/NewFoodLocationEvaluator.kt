package com.dev.bruno.worms.services.evaluation

import com.dev.bruno.worms.domain.MapPoint
import com.dev.bruno.worms.domain.PlayerStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.MatchMapPlayer
import com.dev.bruno.worms.dto.RunningMatch
import kotlin.random.Random

class NewFoodLocationEvaluator : Evaluator() {

    override fun evaluate(runningMatch: RunningMatch, lastMap: MatchMap?, currentMap: MatchMap) {
        val stillPlaying = currentMap.players
                .filter { it.status == PlayerStatus.PLAYING }
        val playersLastPointIsEqualFoodPoint = stillPlaying
                .map { it.position.last() }
                .any { lastPoint -> lastPoint == currentMap.foodPosition }
        if (playersLastPointIsEqualFoodPoint) {
            currentMap.foodPosition = calculateNewFoodPosition(
                    runningMatch,
                    stillPlaying
            )
        }
        next?.evaluate(runningMatch, lastMap, currentMap)
    }

    private fun calculateNewFoodPosition(runningMatch: RunningMatch, players: List<MatchMapPlayer>): MapPoint {
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