package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.MatchPlayerStatus
import com.dev.bruno.worms.dto.Map
import com.dev.bruno.worms.dto.RunningMatch

class SolidWallEvaluator : Evaluator() {

    override fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: Map?,
                              currentMap: Map) {
        val stillPlaying = currentMap.players.filter {
            it.status == MatchPlayerStatus.PLAYING
        }

        stillPlaying.forEach { player ->
            val lastPoint = player.position.last()

            val xIsOutOfMap = lastPoint.x < 0 ||
                    lastPoint.x >= runningMatch.mapSize

            val yIsOutOfMap = lastPoint.y < 0 ||
                    lastPoint.y >= runningMatch.mapSize

            if (xIsOutOfMap || yIsOutOfMap) {
                player.status = MatchPlayerStatus.DEAD
            }
        }
    }
}
