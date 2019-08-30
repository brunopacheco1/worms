package com.dev.bruno.worms.services.evaluation

object RoundEvaluatorFactory {

    fun getRoundEvaluator(): RoundEvaluator {
        val evaluator = BasicInitializerEvaluator()
        evaluator.link(DirectionEvaluator())
                .link(PlayerPositionEvaluator())
                .link(MatchAndPlayersStatusEvaluator())
                .link(NewFoodLocationEvaluator())
                .link(FinishingRoundEvaluator())
        return RoundEvaluator(evaluator)
    }
}