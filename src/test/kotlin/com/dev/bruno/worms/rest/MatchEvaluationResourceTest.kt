package com.dev.bruno.worms.rest

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.dto.PlayerAction
import com.dev.bruno.worms.exceptions.MatchNotStartedException
import com.dev.bruno.worms.helpers.toJson
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
open class MatchEvaluationResourceTest {

    @Test
    fun given_new_player_action_and_not_started_match_when_post_then_throw_exception() {
        val playerAction = PlayerAction(
                1,
                direction = Direction.UP
        )

        given().contentType(ContentType.JSON).body(playerAction.toJson())
                .`when`().put("/v1/match/rounds")
                .then()
                .statusCode(MatchNotStartedException().statusCode)
                .body("message", `is`(MatchNotStartedException().message))
    }
}