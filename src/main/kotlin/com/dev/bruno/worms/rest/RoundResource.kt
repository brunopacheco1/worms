package com.dev.bruno.worms.rest

import com.dev.bruno.worms.dto.PlayerAction
import com.dev.bruno.worms.services.RoundService
import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/v1/match")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
class RoundResource @Inject constructor(
        val roundService: RoundService
) {

    @PUT
    @Path("/{id}/rounds")
    @Consumes(MediaType.APPLICATION_JSON)
    fun movingPlayer(@PathParam("id") id: Long,
                     playerAction: PlayerAction): Response {
        roundService.addAction(id, playerAction)
        return Response.accepted().build()
    }

    @GET
    @Path("/{id}/map")
    fun retrieveMap(@PathParam("id") id: Long): MatchMap {
	    return roundService.generateMap(id)
    }
}
