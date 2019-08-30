package com.dev.bruno.worms.services.evaluation

import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch
import com.dev.bruno.worms.helpers.asMatchMap
import com.dev.bruno.worms.services.MatchPool

class RoundEvaluator(
        val evaluator: Evaluator
) {

    fun evaluate(match: RunningMatch): MatchMap {
        val currentMap = match.asMatchMap()
        val lastMap = MatchPool.getLastMap(match.id)
        evaluator.evaluate(match, lastMap, currentMap)
        return currentMap
    }
}