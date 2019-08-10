package com.dev.bruno.worms.rest

import com.dev.bruno.worms.domain.Player
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/v1/player")
class PlayerResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{nickname}")
    fun createPlayer(@PathParam("nickname") nickname: String): Player {
        throw RuntimeException("Not implemented")
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    fun retrievePlayer(@PathParam("id") id: String): Player {
        throw RuntimeException("Not implemented")
    }
}