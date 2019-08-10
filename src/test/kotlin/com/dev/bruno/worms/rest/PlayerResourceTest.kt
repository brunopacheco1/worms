package com.dev.bruno.worms.rest

import com.dev.bruno.worms.dto.NewPlayer
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.Test

@QuarkusTest
open class PlayerResourceTest {

    @Test
    fun given_nickname_when_post_then_return_player_info() {
        val newPlayer = NewPlayer("nickname_test")

        given()
                .`when`().post("/v1/player/", newPlayer)
                .then()
                .statusCode(200)
                .body("nickname", `is`(newPlayer.nickname),
                        "id", notNullValue())
    }

    @Test
    fun given_player_id_when_get_then_return_player_info() {
        val playerId = "id_test"

        given()
                .`when`().get("/v1/player/{id}", playerId)
                .then()
                .statusCode(200)
                .body("nickname", notNullValue(),
                        "id", `is`(playerId))
    }
}