package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.*
import com.dev.bruno.worms.dto.MapPoint
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.MatchMapPlayer
import com.dev.bruno.worms.dto.RunningMatch
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LongestWormModeEvaluatorTest {

    private val objectUnderTest: Evaluator = LongestWormModeEvaluator()

    @Test
    fun should_set_status_winner_for_the_longest_worm() {
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
                                MatchPlayerStatus.DEAD,
                                3,
                                Direction.UP,
                                arrayListOf(MapPoint(0, 0), MapPoint(0, 1), MapPoint(0, 2))
                        ),
                        MatchMapPlayer(
                                1,
                                MatchPlayerStatus.DEAD,
                                2,
                                Direction.UP,
                                arrayListOf(MapPoint(0, 0), MapPoint(0, 1))
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
                                3,
                                Direction.UP,
                                arrayListOf(MapPoint(0, 0), MapPoint(0, 1), MapPoint(0, 2))
                        ),
                        MatchMapPlayer(
                                1,
                                MatchPlayerStatus.DEAD,
                                2,
                                Direction.UP,
                                arrayListOf(MapPoint(0, 0), MapPoint(0, 1))
                        )
                ),
                MapPoint(5, 5),
                MatchStatus.FINISHED
        )
        objectUnderTest.evaluate(match, null, actualValue)
        Assertions.assertEquals(expectedValue, actualValue)
    }
}