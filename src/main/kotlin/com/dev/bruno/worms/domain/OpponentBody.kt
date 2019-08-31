package com.dev.bruno.worms.domain

import com.dev.bruno.worms.services.evaluation.Evaluator
import com.dev.bruno.worms.services.evaluation.GhostOpponentEvaluator
import com.dev.bruno.worms.services.evaluation.SolidOpponentEvaluator

enum class OpponentBody {
    SOLID,
    GHOST;

    fun getEvaluator(): Evaluator {
        return when (this) {
            SOLID -> SolidOpponentEvaluator()
            GHOST -> GhostOpponentEvaluator()
        }
    }
}
