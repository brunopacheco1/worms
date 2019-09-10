package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.*
import com.dev.bruno.worms.dto.MapPoint
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.MatchMapPlayer
import com.dev.bruno.worms.dto.RunningMatch
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SolidWallEvaluatorTest {

    private val objectUnderTest: Evaluator = SolidWallEvaluator()

    @Test
    fun should_set_players_as_dead_if_last_point_reaches_the_wall() {
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
                                arrayListOf(MapPoint(0, 9), MapPoint(0, 10))
                        ),
                        MatchMapPlayer(
                                2,
                                MatchPlayerStatus.PLAYING,
                                2,
                                Direction.DOWN,
                                arrayListOf(MapPoint(0, 0), MapPoint(0, -1))
                        ),
                        MatchMapPlayer(
                                3,
                                MatchPlayerStatus.PLAYING,
                                2,
                                Direction.RIGHT,
                                arrayListOf(MapPoint(9, 0), MapPoint(10, 0))
                        ),
                        MatchMapPlayer(
                                4,
                                MatchPlayerStatus.PLAYING,
                                2,
                                Direction.LEFT,
                                arrayListOf(MapPoint(0, 0), MapPoint(-1, 0))
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
                                2,
                                Direction.UP,
                                arrayListOf(MapPoint(0, 9), MapPoint(0, 10))
                        ),
                        MatchMapPlayer(
                                2,
                                MatchPlayerStatus.DEAD,
                                2,
                                Direction.DOWN,
                                arrayListOf(MapPoint(0, 0), MapPoint(0, -1))
                        ),
                        MatchMapPlayer(
                                3,
                                MatchPlayerStatus.DEAD,
                                2,
                                Direction.RIGHT,
                                arrayListOf(MapPoint(9, 0), MapPoint(10, 0))
                        ),
                        MatchMapPlayer(
                                4,
                                MatchPlayerStatus.DEAD,
                                2,
                                Direction.LEFT,
                                arrayListOf(MapPoint(0, 0), MapPoint(-1, 0))
                        )
                ),
                MapPoint(5, 5),
                MatchStatus.RUNNING
        )
        objectUnderTest.evaluate(match, null, actualValue)
        Assertions.assertEquals(expectedValue, actualValue)
    }
}