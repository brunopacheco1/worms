package com.dev.bruno.worms.services.evaluation

import com.dev.bruno.worms.domain.MatchStatus
import com.dev.bruno.worms.domain.PlayerStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch

class PlayerStatusEvaluator : Evaluator() {

    override fun evaluate(runningMatch: RunningMatch, lastMap: MatchMap?, currentMap: MatchMap) {
        currentMap.players
                .filter { it.status == PlayerStatus.PLAYING }
                .forEach { player ->
                    val lastPoint = player.position.last()
                    val xIsOutOfMap = lastPoint.x < 0 || lastPoint.x >= runningMatch.mapSize
                    val yIsOutOfMap = lastPoint.y < 0 || lastPoint.y >= runningMatch.mapSize
                    if (xIsOutOfMap || yIsOutOfMap) {
                        player.status = PlayerStatus.DEAD
                    }
                }
        if (currentMap.players.none { it.status == PlayerStatus.PLAYING }) {
            currentMap.status = MatchStatus.FINISHED
        }
        next?.evaluate(runningMatch, lastMap, currentMap)
    }
}