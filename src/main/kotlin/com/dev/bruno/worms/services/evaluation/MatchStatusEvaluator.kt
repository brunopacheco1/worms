package com.dev.bruno.worms.services.evaluation

import com.dev.bruno.worms.domain.MatchStatus
import com.dev.bruno.worms.domain.PlayerRoundStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch

class MatchStatusEvaluator : Evaluator() {

    override fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: MatchMap?,
                              currentMap: MatchMap) {
        if (currentMap.players.none { it.status == PlayerRoundStatus.PLAYING }) {
            currentMap.status = MatchStatus.FINISHED
        }
    }
}
