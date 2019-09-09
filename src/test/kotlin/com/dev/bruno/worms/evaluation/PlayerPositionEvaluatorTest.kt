package com.dev.bruno.worms.rest

import com.dev.bruno.worms.domain.Difficulty
import com.dev.bruno.worms.domain.OpponentBody
import com.dev.bruno.worms.domain.PlayMode
import com.dev.bruno.worms.domain.Wall
import com.dev.bruno.worms.dto.NewMatch
import com.dev.bruno.worms.dto.NewMatchPlayer
import com.dev.bruno.worms.exceptions.MaximumPlayersException
import com.dev.bruno.worms.helpers.toJson
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.undertow.util.StatusCodes
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

class PlayerPositionEvaluatorTest {

    private val objectUnderTest: Evaluator = PlayerPositionEvaluator()

    @Test
    fun should_do_nothing_when_last_map_is_null() {
	    val match = RunningMatch()
	    val actualValue = Map()
	    val expectedValue = Map()
	    objectUnderTest.evaluate(match, null, actualValue)
	    assertThat(map).equalsRecursivily(expectedValue)
    }

    @Test
    fun should_update_players_positions() {                                                  val match = RunningMatch()
            val lastMap = Map()
            val actualValue = Map()
            val expectedValue = Map()
            objectUnderTest.evaluate(match, lastMap, actualValue)
            assertThat(map).equalsRecursivily(expectedValue)
    }

    @Test                                                                            fun should_increase_player_length_when_last_point_is_equal_food() {                      val match = RunningMatch()
            val lastMap = Map()
            val actualValue = Map()
            val expectedValue = Map()
            objectUnderTest.evaluate(match, lastMap, actualValue)
            assertThat(map).equalsRecursivily(expectedValue)                         }

}
