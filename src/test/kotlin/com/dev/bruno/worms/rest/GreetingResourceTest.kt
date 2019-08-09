package com.dev.bruno.worms.rest

import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import java.util.*

@QuarkusTest
open class GreetingResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
                .`when`().get("/v1/hello")
                .then()
                .statusCode(200)
                .body(`is`("hello"))
    }

    @Test
    fun testGreetingEndpoint() {
        val uuid = UUID.randomUUID().toString()
        given()
                .pathParam("name", uuid)
                .`when`().get("/v1/hello/greeting/{name}")
                .then()
                .statusCode(200)
                .body(`is`("hello $uuid"))
    }

}