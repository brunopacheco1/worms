package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.dto.MapPoint
import com.dev.bruno.worms.domain.MatchPlayerStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch

class MirrorWallEvaluator : Evaluator() {

    override fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: MatchMap?,
                              currentMap: MatchMap) {
        val stillPlaying = currentMap.players.filter {
            it.status == MatchPlayerStatus.PLAYING
        }

        stillPlaying.forEach { player ->
            val lastPoint = player.position.last()

            if (lastPoint.x < 0) {
                player.position = player.position.subList(0, player.position.size - 1) +
                        MapPoint(runningMatch.mapSize - 1, lastPoint.y)
            }

            if (lastPoint.x >= runningMatch.mapSize) {
                player.position = player.position.subList(0, player.position.size - 1) +
                        MapPoint(0, lastPoint.y)
            }

            if (lastPoint.y < 0) {
                player.position = player.position.subList(0, player.position.size - 1) +
                        MapPoint(lastPoint.x, runningMatch.mapSize - 1)
            }

            if (lastPoint.y >= runningMatch.mapSize) {
                player.position = player.position.subList(0, player.position.size - 1) +
                        MapPoint(lastPoint.x, 0)
            }
        }
    }
}