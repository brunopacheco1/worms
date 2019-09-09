package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.MatchStatus
import com.dev.bruno.worms.dto.Map
import com.dev.bruno.worms.dto.RunningMatch
import com.dev.bruno.worms.helpers.toJson
import com.dev.bruno.worms.services.MatchPool

class FinishingRoundEvaluator : Evaluator() {

    override fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: Map?,
                              currentMap: Map) {
        if (lastMap == null || lastMap.status != MatchStatus.FINISHED) {
            MatchPool.addMap(currentMap)
            println(currentMap.toJson())
        }
    }
}