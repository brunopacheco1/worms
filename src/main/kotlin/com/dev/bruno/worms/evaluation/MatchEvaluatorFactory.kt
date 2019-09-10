package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.dto.RunningMatch

object MatchEvaluatorFactory {

    fun getEvaluator(match: RunningMatch): MatchEvaluator {
        val evaluator = InitializerEvaluator()
        evaluator.link(DirectionEvaluator())
                .link(PlayerPositionEvaluator())
                .link(match.wall.getEvaluator())
                .link(match.opponentBody.getEvaluator())
                .link(MatchStatusEvaluator())
                .link(NewFoodLocationEvaluator())
                .link(match.playMode.getEvaluator())
        return MatchEvaluator(evaluator)
    }
}
