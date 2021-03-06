package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch

abstract class Evaluator {

    var next: Evaluator? = null

    fun link(next: Evaluator): Evaluator {
        this.next = next
        return next
    }

    abstract fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: MatchMap?,
                              currentMap: MatchMap)

    fun evaluate(runningMatch: RunningMatch,
                 lastMap: MatchMap?,
                 currentMap: MatchMap) {
        doEvaluation(runningMatch, lastMap, currentMap)
        next?.evaluate(runningMatch, lastMap, currentMap)
    }
}
