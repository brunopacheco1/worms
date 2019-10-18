package com.dev.bruno.worms.rest

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.dto.PlayerAction
import com.dev.bruno.worms.exceptions.MatchNotFoundException
import com.dev.bruno.worms.exceptions.MatchNotStartedException
import com.dev.bruno.worms.helpers.toJson
import io.netty.handler.codec.http.HttpResponseStatus
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
open class MatchEvaluationResourceTest {

    @Test
    fun given_new_player_action_and_running_match_when_post_then_accepted() {
        val playerAction = PlayerAction(
                1,
                direction = Direction.UP
        )

        given().contentType(ContentType.JSON).body(playerAction.toJson())
                .`when`().put("/api/v1/match/1/rounds")
                .then()
                .statusCode(HttpResponseStatus.ACCEPTED.code())
    }

    @Test
    fun given_new_player_action_and_not_found_match_when_post_then_throw_exception() {
        val playerAction = PlayerAction(
                1,
                direction = Direction.UP
        )

        given().contentType(ContentType.JSON).body(playerAction.toJson())
                .`when`().put("/api/v1/match/22222/rounds")
                .then()
                .statusCode(MatchNotFoundException().statusCode)
                .body("message", `is`(MatchNotFoundException().message))
    }

    @Test
    fun given_new_player_action_and_not_started_match_when_post_then_throw_exception() {
        val playerAction = PlayerAction(
                1,
                direction = Direction.UP
        )

        given().contentType(ContentType.JSON).body(playerAction.toJson())
                .`when`().put("/api/v1/match/2/rounds")
                .then()
                .statusCode(MatchNotStartedException().statusCode)
                .body("message", `is`(MatchNotStartedException().message))
    }
}

