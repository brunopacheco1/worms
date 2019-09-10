package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.*
import com.dev.bruno.worms.dto.*
import com.dev.bruno.worms.services.MatchPool
import org.junit.jupiter.api.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class DirectionEvaluatorTest {

    private val objectUnderTest: Evaluator = DirectionEvaluator()

    @Test
    @Order(1)
    fun should_keep_direction_if_none_was_found_in_the_pool() {
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
    @Order(2)
    fun should_get_new_direction_if_it_was_found_in_the_pool() {
        MatchPool.addAction(PlayerAction(1, Direction.LEFT))
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
                        Direction.LEFT,
                        arrayListOf(MapPoint(0, 0), MapPoint(0, 1)))
                ),
                MapPoint(5, 5),
                MatchStatus.RUNNING
        )
        objectUnderTest.evaluate(match, null, actualValue)
        Assertions.assertEquals(expectedValue, actualValue)
    }

    @Test
    @Order(3)
    fun should_not_accept_opposite_direction() {
        MatchPool.addAction(PlayerAction(1, Direction.DOWN))
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
}
