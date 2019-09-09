package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.MatchPlayerStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch

class GhostOpponentEvaluator : Evaluator() {

    override fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: MatchMap?,
                              currentMap: MatchMap) {
        val stillPlaying = currentMap.players.filter {
            it.status == MatchPlayerStatus.PLAYING
        }

        stillPlaying.forEach { player ->
            val lastPoint = player.position.last()
            val allOtherPoints = player.position.toHashSet()

            if (allOtherPoints.contains(lastPoint)) {
                player.status = MatchPlayerStatus.DEAD
            }
        }
    }
}
