package com.dev.bruno.worms.services.evaluation

object RoundEvaluatorFactory {

    fun getRoundEvaluator(): Evaluator {
        val evaluator = BasicInitializerEvaluator()
        evaluator.link(DirectionEvaluator())
                .link(PlayerPositionEvaluator())
                .link(PlayerStatusEvaluator())
                .link(NewFoodLocationEvaluator())
        return evaluator
    }
}