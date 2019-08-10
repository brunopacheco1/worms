package com.dev.bruno.worms.rest

import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.PlayerAction
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/v1/match")
@Produces(MediaType.APPLICATION_JSON)
class RoundResource {

    @PUT
    @Path("/{matchId}/rounds")
    @Consumes(MediaType.APPLICATION_JSON)
    fun movingPlayer(
            @PathParam("matchId") matchId: String,
            @RequestBody playerAction: PlayerAction): Response {
        throw RuntimeException("Not implemented")
    }

    @GET
    @Path("/{matchId}/map")
    fun retrieveMap(@PathParam("matchId") matchId: String): MatchMap {
        throw RuntimeException("Not implemented")
    }
}