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

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
open class MatchResourceTest {

    private val newMatch = NewMatch(
            Wall.SOLID,
            OpponentBody.SOLID,
            Difficulty.EASY,
            PlayMode.SURVIVAL,
            1,
            30
    )

    private val newMatchId = 2
    private val existingMatchId = 1
    private val newPlayerId1 = 1
    private val newPlayerId2 = 2

    @Test
    @Order(1)
    fun given_new_match_when_post_then_return_match_info() {
        given().contentType(ContentType.JSON).body(newMatch.toJson())
                .`when`().post("/v1/match")
                .then()
                .statusCode(StatusCodes.OK)
                .body("id", `is`(newMatchId),
                        "wall", `is`(newMatch.wall.name),
                        "opponentBody", `is`(newMatch.opponentBody.name),
                        "difficulty", `is`(newMatch.difficulty.name),
                        "playMode", `is`(newMatch.playMode.name),
                        "numberOfPlayers", `is`(newMatch.numberOfPlayers),
                        "mapSize", `is`(newMatch.mapSize))
    }

    @Test
    @Order(2)
    fun given_new_match_player_and_match_id_when_post_then_return_match_info() {
        val newMatchPlayer = NewMatchPlayer(newPlayerId1.toLong())

        given().contentType(ContentType.JSON).body(newMatchPlayer.toJson())
                .`when`().put("/v1/match/{matchId}/players", newMatchId)
                .then()
                .statusCode(StatusCodes.OK)
                .body("id", `is`(newMatchId),
                        "wall", `is`(newMatch.wall.name),
                        "opponentBody", `is`(newMatch.opponentBody.name),
                        "difficulty", `is`(newMatch.difficulty.name),
                        "playMode", `is`(newMatch.playMode.name),
                        "numberOfPlayers", `is`(newMatch.numberOfPlayers),
                        "mapSize", `is`(newMatch.mapSize),
                        "players[0].id", `is`(newPlayerId1))
    }

    @Test
    @Order(3)
    fun given_another_new_match_player_and_match_id_when_post_then_throw_exception() {
        val newMatchPlayer = NewMatchPlayer(newPlayerId2.toLong())

        given().contentType(ContentType.JSON).body(newMatchPlayer.toJson())
                .`when`().put("/v1/match/{matchId}/players", newMatchId)
                .then()
                .statusCode(MaximumPlayersException().statusCode)
                .body("message", `is`(MaximumPlayersException().message))
    }

    @Test
    @Order(4)
    fun when_get_then_return_match_info_list() {
        given()
                .`when`().get("/v1/match")
                .then()
                .statusCode(StatusCodes.OK)
                .body("[0].id", `is`(existingMatchId),
                        "[1].id", `is`(newMatchId),
                        "[1].wall", `is`(newMatch.wall.name),
                        "[1].opponentBody", `is`(newMatch.opponentBody.name),
                        "[1].difficulty", `is`(newMatch.difficulty.name),
                        "[1].playMode", `is`(newMatch.playMode.name),
                        "[1].numberOfPlayers", `is`(newMatch.numberOfPlayers),
                        "[1].mapSize", `is`(newMatch.mapSize),
                        "[1].players[0].id", `is`(newPlayerId1))
    }

    @Test
    @Order(5)
    fun given_match_id_when_get_then_return_match_info() {
        given()
                .`when`().get("/v1/match/{id}", newMatchId)
                .then()
                .statusCode(StatusCodes.OK)
                .body("id", `is`(newMatchId),
                        "wall", `is`(newMatch.wall.name),
                        "opponentBody", `is`(newMatch.opponentBody.name),
                        "difficulty", `is`(newMatch.difficulty.name),
                        "playMode", `is`(newMatch.playMode.name),
                        "numberOfPlayers", `is`(newMatch.numberOfPlayers),
                        "mapSize", `is`(newMatch.mapSize),
                        "players[0].id", `is`(newPlayerId1))
    }
}