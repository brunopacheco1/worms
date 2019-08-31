package com.dev.bruno.worms.services.evaluation

import com.dev.bruno.worms.dto.RunningMatch

object RoundEvaluatorFactory {

    fun getRoundEvaluator(match: RunningMatch): RoundEvaluator {
        val evaluator = BasicInitializerEvaluator()
        evaluator.link(DirectionEvaluator())
                .link(PlayerPositionEvaluator())
                .link(match.wall.getEvaluator())
                .link(match.opponentBody.getEvaluator())
                .link(MatchStatusEvaluator())
                .link(NewFoodLocationEvaluator())
                .link(match.playMode.getEvaluator())
                .link(FinishingRoundEvaluator())
        return RoundEvaluator(evaluator)
    }
}
