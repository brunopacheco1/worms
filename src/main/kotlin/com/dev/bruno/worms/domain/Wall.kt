package com.dev.bruno.worms.domain

import com.dev.bruno.worms.evaluation.Evaluator
import com.dev.bruno.worms.evaluation.MirrorWallEvaluator
import com.dev.bruno.worms.evaluation.SolidWallEvaluator

enum class Wall {
    SOLID,
    MIRROR;

    fun getEvaluator(): Evaluator {
        return when (this) {
            SOLID -> SolidWallEvaluator()
            MIRROR -> MirrorWallEvaluator()
        }
    }
}
