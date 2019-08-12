package com.dev.bruno.worms.rest

import com.dev.bruno.worms.domain.Difficulty
import com.dev.bruno.worms.domain.GameMode
import com.dev.bruno.worms.domain.GamePlay
import com.dev.bruno.worms.domain.PlayerMode
import com.dev.bruno.worms.dto.NewMatch
import com.dev.bruno.worms.dto.NewMatchPlayer
import com.dev.bruno.worms.helpers.toJson
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.Test

@QuarkusTest
open class MatchResourceTest {

    @Test
    fun given_new_match_when_post_then_return_match_info() {
        val newMatch = NewMatch(
                GameMode.CLASSIC,
                GamePlay.ARROWS,
                Difficulty.EASY,
                PlayerMode.SOLID,
                1,
                30
        )

        given().contentType(ContentType.JSON).body(newMatch.toJson())
                .`when`().post("/v1/match")
                .then()
                .statusCode(200)
                .body("id", notNullValue(),
                        "gameMode", `is`(newMatch.gameMode),
                        "gamePlay", `is`(newMatch.gamePlay),
                        "difficulty", `is`(newMatch.difficulty),
                        "playerMode", `is`(newMatch.playerMode),
                        "numberOfPlayers", `is`(newMatch.numberOfPlayers),
                        "mapSize", `is`(newMatch.mapSize))
    }

    @Test
    fun given_new_match_player_and_match_id_when_post_then_return_match_info() {
        val newMatch = NewMatch(
                GameMode.CLASSIC,
                GamePlay.ARROWS,
                Difficulty.EASY,
                PlayerMode.SOLID,
                1,
                30
        )

        val newMatchPlayer = NewMatchPlayer(
                "player_id_test"
        )

        val matchId = "match_id_test"

        given().contentType(ContentType.JSON).body(newMatchPlayer.toJson())
                .`when`().put("/v1/match/{matchId}/players", matchId)
                .then()
                .statusCode(200)
                .body("id", notNullValue(),
                        "gameMode", `is`(newMatch.gameMode),
                        "gamePlay", `is`(newMatch.gamePlay),
                        "difficulty", `is`(newMatch.difficulty),
                        "playerMode", `is`(newMatch.playerMode),
                        "numberOfPlayers", `is`(newMatch.numberOfPlayers),
                        "mapSize", `is`(newMatch.mapSize))
    }

    @Test
    fun when_get_then_return_match_info_list() {
        val newMatch = NewMatch(
                GameMode.CLASSIC,
                GamePlay.ARROWS,
                Difficulty.EASY,
                PlayerMode.SOLID,
                1,
                30
        )

        given()
                .`when`().get("/v1/match")
                .then()
                .statusCode(200)
                .body("[0].id", notNullValue(),
                        "[0].gameMode", `is`(newMatch.gameMode),
                        "[0].gamePlay", `is`(newMatch.gamePlay),
                        "[0].difficulty", `is`(newMatch.difficulty),
                        "[0].playerMode", `is`(newMatch.playerMode),
                        "[0].numberOfPlayers", `is`(newMatch.numberOfPlayers),
                        "[0].mapSize", `is`(newMatch.mapSize))
    }

    @Test
    fun given_match_id_when_get_then_return_match_info() {
        val newMatch = NewMatch(
                GameMode.CLASSIC,
                GamePlay.ARROWS,
                Difficulty.EASY,
                PlayerMode.SOLID,
                1,
                30
        )

        val matchId = "match_id_test"

        given()
                .`when`().get("/v1/match/{id}", matchId)
                .then()
                .statusCode(200)
                .body("id", notNullValue(),
                        "gameMode", `is`(newMatch.gameMode),
                        "gamePlay", `is`(newMatch.gamePlay),
                        "difficulty", `is`(newMatch.difficulty),
                        "playerMode", `is`(newMatch.playerMode),
                        "numberOfPlayers", `is`(newMatch.numberOfPlayers),
                        "mapSize", `is`(newMatch.mapSize))
    }
}