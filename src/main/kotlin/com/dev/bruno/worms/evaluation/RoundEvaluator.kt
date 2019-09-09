package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.dto.Map
import com.dev.bruno.worms.dto.RunningMatch
import com.dev.bruno.worms.helpers.asMatchMap
import com.dev.bruno.worms.services.MatchPool

class RoundEvaluator(
        val evaluator: Evaluator
) {

    fun evaluate(match: RunningMatch): Map {
        val currentMap = match.asMatchMap()
        val lastMap = MatchPool.getLastMap(match.id)
        evaluator.evaluate(match, lastMap, currentMap)
        return currentMap
    }
}