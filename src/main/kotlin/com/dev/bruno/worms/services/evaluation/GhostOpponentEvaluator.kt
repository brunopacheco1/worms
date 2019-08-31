package com.dev.bruno.worms.services.evaluation

import com.dev.bruno.worms.domain.PlayerRoundStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch

class GhostOpponentEvaluator : Evaluator() {

    override fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: MatchMap?,
                              currentMap: MatchMap) {
        val stillPlaying = currentMap.players.filter {
            it.status == PlayerRoundStatus.PLAYING
        }

        stillPlaying.forEach { player ->
            val lastPoint = player.position.last()
            val allOtherPoints = player.position.toHashSet()

            if (allOtherPoints.contains(lastPoint)) {
                player.status = PlayerRoundStatus.DEAD
            }
        }
    }
}
