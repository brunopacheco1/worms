package com.dev.bruno.worms.rest

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import org.junit.jupiter.api.Test

@QuarkusTest
open class RoundResourceTest {

    @Test
    fun given_nickname_when_post_then_return_player_info() {
        val matchId = "match_id_test"
        val playerId = "player_id_test"
        val direction = "UP"

        RestAssured.given()
                .`when`().post("/v1/round/{matchId}/round/{playerId}/{direction}", matchId, playerId, direction)
                .then()
                .statusCode(200)
    }
}