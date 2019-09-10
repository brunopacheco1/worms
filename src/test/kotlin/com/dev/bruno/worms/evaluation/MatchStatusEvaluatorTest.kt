package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.*
import com.dev.bruno.worms.dto.MapPoint
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.MatchMapPlayer
import com.dev.bruno.worms.dto.RunningMatch
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MatchStatusEvaluatorTest {

    private val objectUnderTest: Evaluator = MatchStatusEvaluator()

    @Test
    fun should_set_match_status_finished_if_all_players_are_dead() {
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

    @Test
    fun should_keep_match_status_if_any_player_is_playing_yet() {
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
                MatchStatus.RUNNING
        )
        objectUnderTest.evaluate(match, null, actualValue)
        Assertions.assertEquals(expectedValue, actualValue)
    }
}