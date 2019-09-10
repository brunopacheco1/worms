package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.*
import com.dev.bruno.worms.dto.MapPoint
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.MatchMapPlayer
import com.dev.bruno.worms.dto.RunningMatch
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SurvivalModeEvaluatorTest {

    private val objectUnderTest: Evaluator = SurvivalModeEvaluator()

    @Test
    fun should_set_player_as_winner_if_it_is_the_last_survival_in_the_last_round() {
        val match = RunningMatch(
                0,
                Wall.SOLID,
                OpponentBody.SOLID,
                Difficulty.EASY,
                PlayMode.SURVIVAL,
                10,
                arrayListOf(1)
        )
        val lastMap = MatchMap(
                0,
                1,
                10,
                arrayListOf(
                        MatchMapPlayer(
                                1,
                                MatchPlayerStatus.PLAYING,
                                2,
                                Direction.UP,
                                arrayListOf(MapPoint(0, 1), MapPoint(0, 2))
                        ),
                        MatchMapPlayer(
                                2,
                                MatchPlayerStatus.PLAYING,
                                2,
                                Direction.DOWN,
                                arrayListOf(MapPoint(0, 9), MapPoint(0, 8))
                        )
                ),
                MapPoint(5, 5),
                MatchStatus.RUNNING
        )
        val actualValue = MatchMap(
                0,
                1,
                10,
                arrayListOf(
                        MatchMapPlayer(
                                1,
                                MatchPlayerStatus.DEAD,
                                2,
                                Direction.UP,
                                arrayListOf(MapPoint(0, 1), MapPoint(0, 2))
                        ),
                        MatchMapPlayer(
                                2,
                                MatchPlayerStatus.DEAD,
                                2,
                                Direction.DOWN,
                                arrayListOf(MapPoint(0, 9), MapPoint(0, 8))
                        )
                ),
                MapPoint(5, 5),
                MatchStatus.FINISHED
        )
        val expectedValue = MatchMap(
                0,
                1,
                10,
                arrayListOf(
                        MatchMapPlayer(
                                1,
                                MatchPlayerStatus.WINNER,
                                2,
                                Direction.UP,
                                arrayListOf(MapPoint(0, 1), MapPoint(0, 2))
                        ),
                        MatchMapPlayer(
                                2,
                                MatchPlayerStatus.WINNER,
                                2,
                                Direction.DOWN,
                                arrayListOf(MapPoint(0, 9), MapPoint(0, 8))
                        )
                ),
                MapPoint(5, 5),
                MatchStatus.FINISHED
        )
        objectUnderTest.evaluate(match, lastMap, actualValue)
        Assertions.assertEquals(expectedValue, actualValue)
    }
}