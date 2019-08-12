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

    @Test
    fun given_nickname_when_post_then_return_player_info() {
        val matchId = "match_id_test"

        val playerAction = PlayerAction(
                "player_id_test",
                direction = Direction.UP
        )

        given().contentType(ContentType.JSON).body(playerAction.toJson())
                .`when`().post("/v1/match/{matchId}/rounds", matchId)
                .then()
                .statusCode(200)
    }
}