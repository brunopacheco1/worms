package com.dev.bruno.worms.domain

import com.dev.bruno.worms.services.evaluation.Evaluator
import com.dev.bruno.worms.services.evaluation.LongestWormModeEvaluator
import com.dev.bruno.worms.services.evaluation.SurvivalModeEvaluator

enum class PlayMode {
    SURVIVAL,
    LONGEST_WORM;

    fun getEvaluator(): Evaluator {
        return when (this) {
            SURVIVAL -> SurvivalModeEvaluator()
            LONGEST_WORM -> LongestWormModeEvaluator()
        }
    }
}
