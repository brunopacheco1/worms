package com.dev.bruno.worms.domain

import com.dev.bruno.worms.evaluation.Evaluator
import com.dev.bruno.worms.evaluation.GhostOpponentEvaluator
import com.dev.bruno.worms.evaluation.SolidOpponentEvaluator

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
