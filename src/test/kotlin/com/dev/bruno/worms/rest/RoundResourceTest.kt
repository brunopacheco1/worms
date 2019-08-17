package com.dev.bruno.worms.rest

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.dto.PlayerAction
import com.dev.bruno.worms.helpers.toJson
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.junit.jupiter.api.Test

@QuarkusTest
open class RoundResourceTest {

    private val matchId = 3;

    @Test
    fun given_nickname_when_post_then_return_player_info() {
        val playerAction = PlayerAction(
                1,
                direction = Direction.UP
        )

        given().contentType(ContentType.JSON).body(playerAction.toJson())
                .`when`().put("/v1/match/{matchId}/rounds", matchId)
                .then()
                .statusCode(202)
    }
}