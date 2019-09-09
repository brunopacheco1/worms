package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.*
import com.dev.bruno.worms.dto.MapPoint
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.MatchMapPlayer
import com.dev.bruno.worms.dto.RunningMatch
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PlayerPositionEvaluatorTest {

    private val objectUnderTest: Evaluator = PlayerPositionEvaluator()

    @Test
    fun should_do_nothing_when_last_map_is_null() {
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
    fun should_update_players_positions() {
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
                        Direction.UP,
                        arrayListOf(MapPoint(0, 0), MapPoint(0, 1)))
                ),
                MapPoint(5, 5),
                MatchStatus.RUNNING
        )
        val actualValue = MatchMap(
                0,
                2,
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
        val expectedValue = MatchMap(
                0,
                2,
                10,
                arrayListOf(MatchMapPlayer(
                        1,
                        MatchPlayerStatus.PLAYING,
                        2,
                        Direction.UP,
                        arrayListOf(MapPoint(0, 1), MapPoint(0, 2)))
                ),
                MapPoint(5, 5),
                MatchStatus.RUNNING
        )
        objectUnderTest.evaluate(match, lastMap, actualValue)
        Assertions.assertEquals(expectedValue, actualValue)
    }

    @Test
    fun should_increase_player_length_when_last_point_is_equal_food() {
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
                        Direction.UP,
                        arrayListOf(MapPoint(0, 0), MapPoint(0, 1)))
                ),
                MapPoint(0, 2),
                MatchStatus.RUNNING
        )
        val actualValue = MatchMap(
                0,
                2,
                10,
                arrayListOf(MatchMapPlayer(
                        1,
                        MatchPlayerStatus.PLAYING,
                        2,
                        Direction.UP,
                        arrayListOf(MapPoint(0, 0), MapPoint(0, 1)))
                ),
                MapPoint(0, 2),
                MatchStatus.RUNNING
        )
        val expectedValue = MatchMap(
                0,
                2,
                10,
                arrayListOf(MatchMapPlayer(
                        1,
                        MatchPlayerStatus.PLAYING,
                        3,
                        Direction.UP,
                        arrayListOf(MapPoint(0, 0), MapPoint(0, 1), MapPoint(0, 2)))
                ),
                MapPoint(0, 2),
                MatchStatus.RUNNING
        )
        objectUnderTest.evaluate(match, lastMap, actualValue)
        Assertions.assertEquals(expectedValue, actualValue)
    }

}
