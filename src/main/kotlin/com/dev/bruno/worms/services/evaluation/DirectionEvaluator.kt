package com.dev.bruno.worms.services.evaluation

import com.dev.bruno.worms.domain.PlayerStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch
import com.dev.bruno.worms.services.PlayerMatchPool

class DirectionEvaluator : Evaluator() {

    override fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: MatchMap?,
                              currentMap: MatchMap) {
        currentMap.players
                .filter { it.status == PlayerStatus.PLAYING }
                .forEach { player ->
                    val newDirection = getDirection(player.direction)
                    if (!newDirection.isOpposite(player.direction)) {
                        player.direction = newDirection
                    }
                }
    }

    private fun getDirection(p: MatchMapPlayer): Direction {
        return PlayerMatchPool.playerActions[p.playerId] ?: p.direction
    }
}
