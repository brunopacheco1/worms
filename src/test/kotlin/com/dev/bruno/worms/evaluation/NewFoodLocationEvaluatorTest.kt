package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.*
import com.dev.bruno.worms.dto.MapPoint
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.MatchMapPlayer
import com.dev.bruno.worms.dto.RunningMatch
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NewFoodLocationEvaluatorTest {

    private val objectUnderTest: Evaluator = NewFoodLocationEvaluator()

    @Test
    fun should_set_new_random_food_position_if_it_was_eaten_by_any_player() {
        val match = RunningMatch(
                0,
                Wall.SOLID,
                OpponentBody.SOLID,
                Difficulty.EASY,
                PlayMode.SURVIVAL,
                10,
                arrayListOf(1)
        )
        val foodPosition = MapPoint(0, 2)
        val actualValue = MatchMap(
                0,
                1,
                10,
                arrayListOf(
                        MatchMapPlayer(
                                1,
                                MatchPlayerStatus.PLAYING,
                                3,
                                Direction.UP,
                                arrayListOf(MapPoint(0, 0), MapPoint(0, 1), MapPoint(0, 2))
                        )
                ),
                foodPosition,
                MatchStatus.RUNNING
        )
        objectUnderTest.evaluate(match, null, actualValue)
        Assertions.assertNotEquals(foodPosition, actualValue.foodPosition)
    }
}