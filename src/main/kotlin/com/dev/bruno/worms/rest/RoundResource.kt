package com.dev.bruno.worms.rest

import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.PlayerAction
import com.dev.bruno.worms.services.MatchService
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/v1/match")
@Produces(MediaType.APPLICATION_JSON)
class RoundResource {

    @Inject
    lateinit var matchService: MatchService

    @PUT
    @Path("/{matchId}/rounds")
    @Consumes(MediaType.APPLICATION_JSON)
    fun movingPlayer(
            @PathParam("matchId") matchId: Long,
            @RequestBody playerAction: PlayerAction): Response {
        matchService.addAction(matchId, playerAction)
        return Response.accepted().build()
    }

    @GET
    @Path("/{matchId}/map")
    fun retrieveMap(@PathParam("matchId") matchId: String): MatchMap {
        throw RuntimeException("Not implemented")
    }
}