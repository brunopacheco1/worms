package com.dev.bruno.worms.rest

import com.dev.bruno.worms.dto.NewPlayer
import com.dev.bruno.worms.dto.PlayerInfo
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/v1/player")
@Produces(MediaType.APPLICATION_JSON)
class PlayerResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun createPlayer(newPlayer: NewPlayer): PlayerInfo {
        return PlayerInfo(
                "test_id",
                newPlayer.nickname
        )
    }

    @GET
    @Path("/{id}")
    fun retrievePlayer(@PathParam("id") id: String): PlayerInfo {
        throw RuntimeException("Not implemented")
    }
}