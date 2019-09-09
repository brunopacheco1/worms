package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.domain.MatchPlayerStatus
import com.dev.bruno.worms.dto.Map
import com.dev.bruno.worms.dto.MapPlayer
import com.dev.bruno.worms.dto.RunningMatch
import com.dev.bruno.worms.services.MatchPool

class DirectionEvaluator : Evaluator() {

    override fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: Map?,
                              currentMap: Map) {
        currentMap.players
                .filter { it.status == MatchPlayerStatus.PLAYING }
                .forEach { player ->
                    val newDirection = getDirection(player)
                    if (!newDirection.isOpposite(player.direction)) {
                        player.direction = newDirection
                    }
                }
    }

    private fun getDirection(p: MapPlayer): Direction {
        return MatchPool.getDirection(p.playerId) ?: p.direction
    }
}