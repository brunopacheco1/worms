package com.dev.bruno.worms.rest

import com.dev.bruno.worms.dto.NewPlayer
import com.dev.bruno.worms.dto.PlayerInfo
import com.dev.bruno.worms.services.PlayerService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/v1/player")
@Produces(MediaType.APPLICATION_JSON)
class PlayerResource {

    @Inject
    lateinit var playerService: PlayerService

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun addPlayer(newPlayer: NewPlayer) = playerService.addPlayer(newPlayer)

    @GET
    @Path("/{id}")
    fun retrievePlayer(@PathParam("id") id: String): PlayerInfo {
        throw RuntimeException("Not implemented")
    }
}