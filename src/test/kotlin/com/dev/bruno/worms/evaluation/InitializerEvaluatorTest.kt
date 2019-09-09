package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.*
import com.dev.bruno.worms.dto.MatchMapPlayer
import com.dev.bruno.worms.dto.MapPoint
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch
import com.dev.bruno.worms.helpers.asMatchMap
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class InitializerEvaluatorTest {

    private val objectUnderTest: Evaluator = InitializerEvaluator()

    @Test
    fun should_fill_map_with_initial_values() {
        val match = RunningMatch(
                0,
                Wall.SOLID,
                OpponentBody.SOLID,
                Difficulty.EASY,
                PlayMode.SURVIVAL,
                10,
                arrayListOf(1)
        )
        val actualValue = match.asMatchMap()
        val expectedValue = MatchMap(
                0,
                1,
                10,
                arrayListOf(MatchMapPlayer(
                        1,
                        MatchPlayerStatus.PLAYING,
                        2,
                        Direction.UP,
                        arrayListOf(MapPoint(0, 0), MapPoint(0, 1)))
                ),
                MapPoint(5, 5),
                MatchStatus.RUNNING
        )
        objectUnderTest.evaluate(match, null, actualValue)
        Assertions.assertEquals(expectedValue, actualValue)
    }

    @Test
    fun should_fill_map_with_last_map_values() {
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
                arrayListOf(MatchMapPlayer(
                        1,
                        MatchPlayerStatus.PLAYING,
                        2,
                        Direction.DOWN,
                        arrayListOf(MapPoint(0, 0), MapPoint(0, 1)))
                ),
                MapPoint(6, 6),
                MatchStatus.RUNNING
        )
        val actualValue = match.asMatchMap()
        val expectedValue = MatchMap(
                0,
                2,
                10,
                arrayListOf(MatchMapPlayer(
                        1,
                        MatchPlayerStatus.PLAYING,
                        2,
                        Direction.DOWN,
                        arrayListOf(MapPoint(0, 0), MapPoint(0, 1)))
                ),
                MapPoint(6, 6),
                MatchStatus.RUNNING
        )
        objectUnderTest.evaluate(match, lastMap, actualValue)
        Assertions.assertEquals(expectedValue, actualValue)
    }
}
