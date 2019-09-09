package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.dto.Map
import com.dev.bruno.worms.dto.RunningMatch

abstract class Evaluator {

    var next: Evaluator? = null

    fun link(next: Evaluator): Evaluator {
        this.next = next
        return next
    }

    abstract fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: Map?,
                              currentMap: Map)

    fun evaluate(runningMatch: RunningMatch,
                 lastMap: Map?,
                 currentMap: Map) {
        doEvaluation(runningMatch, lastMap, currentMap)
        next?.evaluate(runningMatch, lastMap, currentMap)
    }
}
