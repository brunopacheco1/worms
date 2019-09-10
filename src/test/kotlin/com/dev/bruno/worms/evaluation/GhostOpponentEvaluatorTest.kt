package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.*
import com.dev.bruno.worms.dto.MapPoint
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.MatchMapPlayer
import com.dev.bruno.worms.dto.RunningMatch
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GhostOpponentEvaluatorTest {

    private val objectUnderTest: Evaluator = GhostOpponentEvaluator()

    @Test
    fun should_set_player_status_dead_when_last_point_is_equals_any_other_point_of_the_same_player() {
        val match = RunningMatch(
                0,
                Wall.SOLID,
                OpponentBody.SOLID,
                Difficulty.EASY,
                PlayMode.SURVIVAL,
                10,
                arrayListOf(1)
        )
        val actualValue = MatchMap(
                0,
                1,
                10,
                arrayListOf(
                        MatchMapPlayer(
                                1,
                                MatchPlayerStatus.PLAYING,
                                5,
                                Direction.UP,
                                arrayListOf(MapPoint(0, 0), MapPoint(0, 1), MapPoint(1, 1), MapPoint(1, 0), MapPoint(0, 0))
                        )
                ),
                MapPoint(5, 5),
                MatchStatus.RUNNING
        )
        val expectedValue = MatchMap(
                0,
                1,
                10,
                arrayListOf(
                        MatchMapPlayer(
                                1,
                                MatchPlayerStatus.DEAD,
                                5,
                                Direction.UP,
                                arrayListOf(MapPoint(0, 0), MapPoint(0, 1), MapPoint(1, 1), MapPoint(1, 0), MapPoint(0, 0))
                        )
                ),
                MapPoint(5, 5),
                MatchStatus.RUNNING
        )
        objectUnderTest.evaluate(match, null, actualValue)
        Assertions.assertEquals(expectedValue, actualValue)
    }

    @Test
    fun should_ignore_opponents() {
        val match = RunningMatch(
                0,
                Wall.SOLID,
                OpponentBody.SOLID,
                Difficulty.EASY,
                PlayMode.SURVIVAL,
                10,
                arrayListOf(1)
        )
        val actualValue = MatchMap(
                0,
                1,
                10,
                arrayListOf(
                        MatchMapPlayer(
                                1,
                                MatchPlayerStatus.PLAYING,
                                2,
                                Direction.UP,
                                arrayListOf(MapPoint(0, 0), MapPoint(0, 1))
                        ),
                        MatchMapPlayer(
                                1,
                                MatchPlayerStatus.PLAYING,
                                2,
                                Direction.UP,
                                arrayListOf(MapPoint(0, 0), MapPoint(0, 1))
                        )
                ),
                MapPoint(5, 5),
                MatchStatus.RUNNING
        )
        val expectedValue = MatchMap(
                0,
                1,
                10,
                arrayListOf(
                        MatchMapPlayer(
                                1,
                                MatchPlayerStatus.PLAYING,
                                2,
                                Direction.UP,
                                arrayListOf(MapPoint(0, 0), MapPoint(0, 1))
                        ),
                        MatchMapPlayer(
                                1,
                                MatchPlayerStatus.PLAYING,
                                2,
                                Direction.UP,
                                arrayListOf(MapPoint(0, 0), MapPoint(0, 1))
                        )
                ),
                MapPoint(5, 5),
                MatchStatus.RUNNING
        )
        objectUnderTest.evaluate(match, null, actualValue)
        Assertions.assertEquals(expectedValue, actualValue)
    }
}