package com.dev.bruno.worms.services.evaluation

object RoundEvaluatorFactory {

    fun getRoundEvaluator(match: RunningMatch): RoundEvaluator {
        val evaluator = BasicInitializerEvaluator()
        evaluator.link(DirectionEvaluator())
                .link(PlayerPositionEvaluator())
                .link(MatchAndPlayersStatusEvaluator())
                .link(NewFoodLocationEvaluator())
                .link(FinishingRoundEvaluator())
        return RoundEvaluator(evaluator)
    }
}
