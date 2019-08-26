package com.dev.bruno.worms.services.evaluation

import com.dev.bruno.worms.domain.PlayerStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch
import com.dev.bruno.worms.services.PlayerMatchPool

class DirectionEvaluator : Evaluator() {

    override fun evaluate(runningMatch: RunningMatch, lastMap: MatchMap?, currentMap: MatchMap) {
        currentMap.players
                .filter { it.status == PlayerStatus.PLAYING }
                .forEach { player ->
                    val newDirection = PlayerMatchPool.playerActions[player.playerId] ?: player.direction
                    if (!newDirection.isOpposite(player.direction)) {
                        player.direction = newDirection
                    }
                }
        next?.evaluate(runningMatch, lastMap, currentMap)
    }
}