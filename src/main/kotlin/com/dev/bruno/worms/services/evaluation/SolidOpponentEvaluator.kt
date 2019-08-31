package com.dev.bruno.worms.services.evaluation

import com.dev.bruno.worms.domain.PlayerRoundStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch

class SolidOpponentEvaluator : Evaluator() {

    override fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: MatchMap?,
                              currentMap: MatchMap) {
        val stillPlaying = currentMap.players.filter {
            it.status == PlayerRoundStatus.PLAYING
        }

        stillPlaying.forEach { player ->
            val lastPoint = player.position.last()

            val allOtherPoints = stillPlaying
                    .flatMap {
                        if (it.playerId == player.playerId) {
                            it.position.subList(0, it.position.size - 1)
                        } else {
                            it.position
                        }
                    }.toHashSet()

            if (allOtherPoints.contains(lastPoint)) {
                player.status = PlayerRoundStatus.DEAD
            }
        }
    }
}
