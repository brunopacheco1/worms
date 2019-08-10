package com.dev.bruno.worms.rest

import com.dev.bruno.worms.dto.MatchInfo
import com.dev.bruno.worms.dto.NewMatch
import com.dev.bruno.worms.dto.NewMatchPlayer
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/v1/match")
@Produces(MediaType.APPLICATION_JSON)
class MatchResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun createMatch(@RequestBody newMatch: NewMatch): MatchInfo {
        throw RuntimeException("Not implemented")
    }

    @PUT
    @Path("/{matchId}/players")
    @Consumes(MediaType.APPLICATION_JSON)
    fun addPlayer(@PathParam("matchId") matchId: String,
                  @RequestBody newMatchPlayer: NewMatchPlayer): MatchInfo {
        throw RuntimeException("Not implemented")
    }

    @GET
    fun retrieveMatches(): Set<MatchInfo> {
        throw RuntimeException("Not implemented")
    }

    @GET
    @Path("/{id}")
    fun retrieveMatch(@PathParam("id") id: String): MatchInfo {
        throw RuntimeException("Not implemented")
    }
}