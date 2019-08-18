package com.dev.bruno.worms.rest

import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.PlayerAction
import com.dev.bruno.worms.services.RoundService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/v1/match")
@Produces(MediaType.APPLICATION_JSON)
class RoundResource {

    @Inject
    lateinit var roundService: RoundService

    @PUT
    @Path("/{matchId}/rounds")
    @Consumes(MediaType.APPLICATION_JSON)
    fun movingPlayer(@PathParam("matchId") matchId: Long, playerAction: PlayerAction): Response {
        roundService.addAction(matchId, playerAction)
        return Response.accepted().build()
    }

    @GET
    @Path("/{matchId}/map")
    fun retrieveMap(@PathParam("matchId") matchId: String): MatchMap {
        throw RuntimeException("Not implemented")
    }
}