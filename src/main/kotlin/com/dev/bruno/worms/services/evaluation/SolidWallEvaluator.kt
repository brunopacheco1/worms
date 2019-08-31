package com.dev.bruno.worms.services.evaluation

import com.dev.bruno.worms.domain.PlayerRoundStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch

class SolidWallEvaluator : Evaluator() {

    override fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: MatchMap?,
                              currentMap: MatchMap) {
        val stillPlaying = currentMap.players.filter {
            it.status == PlayerRoundStatus.PLAYING
        }

        stillPlaying.forEach { player ->
            val lastPoint = player.position.last()

            val xIsOutOfMap = lastPoint.x < 0 ||
                    lastPoint.x >= runningMatch.mapSize

            val yIsOutOfMap = lastPoint.y < 0 ||
                    lastPoint.y >= runningMatch.mapSize

            if (xIsOutOfMap || yIsOutOfMap) {
                player.status = PlayerRoundStatus.DEAD
            }
        }
    }
}
