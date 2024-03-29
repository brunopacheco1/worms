package com.dev.bruno.worms.rest

import com.dev.bruno.worms.dto.NewPlayer
import com.dev.bruno.worms.helpers.toJson
import io.netty.handler.codec.http.HttpResponseStatus
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
open class PlayerResourceTest {

    private val newPlayer = NewPlayer("nickname_test")
    private val existingPlayerId = 1
    private val existingPlayerNickname = "player_test_1"

    @Test
    @Order(1)
    fun given_new_player_nickname_when_post_then_return_player_info() {
        given().contentType(ContentType.JSON).body(newPlayer.toJson())
                .`when`().post("/api/v1/player/")
                .then()
                .statusCode(HttpResponseStatus.OK.code())
                .body("id", `is`(3),
                        "nickname", `is`(newPlayer.nickname))
    }

    @Test
    @Order(2)
    fun given_player_id_when_get_then_return_player_info() {
        given()
                .`when`().get("/api/v1/player/{id}", existingPlayerId)
                .then()
                .statusCode(HttpResponseStatus.OK.code())
                .body("id", `is`(existingPlayerId),
                        "nickname", `is`(existingPlayerNickname))
    }

    @Test
    @Order(3)
    fun given_existing_nickname_when_post_then_return_existing_player_info() {
        given().contentType(ContentType.JSON).body(newPlayer.toJson())
                .`when`().post("/api/v1/player/")
                .then()
                .statusCode(HttpResponseStatus.OK.code())
                .body("id", `is`(3),
                        "nickname", `is`(newPlayer.nickname))
    }
}
