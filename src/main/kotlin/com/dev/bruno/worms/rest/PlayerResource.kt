package com.dev.bruno.worms.rest

import com.dev.bruno.worms.dto.NewPlayer
import com.dev.bruno.worms.dto.PlayerInfo
import com.dev.bruno.worms.services.PlayerService
import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/api/v1/player")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
class PlayerResource @Inject constructor(
    val playerService: PlayerService
) {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun addPlayer(newPlayer: NewPlayer): PlayerInfo {
        return playerService.add(newPlayer)
    }

    @GET
    @Path("/{id}")
    fun getPlayer(@PathParam("id") id: Long): PlayerInfo {
        return playerService.getPlayer(id)
    }
}
