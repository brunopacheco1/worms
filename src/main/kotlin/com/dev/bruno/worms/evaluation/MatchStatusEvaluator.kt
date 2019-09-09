package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.MatchStatus
import com.dev.bruno.worms.domain.MatchPlayerStatus
import com.dev.bruno.worms.dto.Map
import com.dev.bruno.worms.dto.RunningMatch

class MatchStatusEvaluator : Evaluator() {

    override fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: Map?,
                              currentMap: Map) {
        if (currentMap.players.none { it.status == MatchPlayerStatus.PLAYING }) {
            currentMap.status = MatchStatus.FINISHED
        }
    }
}
