package com.dev.bruno.worms.rest

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.dto.Map
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/v1/round")
class RoundResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{playerId}/{direction}")
    fun movingPlayer(@PathParam("playerId") playerId: String,
                     @PathParam("direction") direction: Direction): Response {
        throw RuntimeException("Not implemented")
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/map/{matchId}")
    fun retrieveMap(@PathParam("matchId") matchId: String): Map {
        throw RuntimeException("Not implemented")
    }
}