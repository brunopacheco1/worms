package com.dev.bruno.worms.services.evaluation

object RoundEvaluatorFactory {

    fun getRoundEvaluator(): Evaluator {
        val evaluator = BasicInitializerEvaluator()
        evaluator.link(DirectionEvaluator())
                .link(PlayerPositionEvaluator())
                .link(MatchAndPlayersStatusEvaluator())
                .link(NewFoodLocationEvaluator())
        return evaluator
    }
}