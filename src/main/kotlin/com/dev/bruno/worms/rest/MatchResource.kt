package com.dev.bruno.worms.rest

import com.dev.bruno.worms.dto.MatchInfo
import com.dev.bruno.worms.dto.NewMatch
import com.dev.bruno.worms.dto.NewMatchPlayer
import com.dev.bruno.worms.services.MatchService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/v1/match")
@Produces(MediaType.APPLICATION_JSON)
class MatchResource {

    @Inject
    lateinit var matchService: MatchService

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun addMatch(newMatch: NewMatch) = matchService.add(newMatch)

    @PUT
    @Path("/{matchId}/players")
    @Consumes(MediaType.APPLICATION_JSON)
    fun addPlayerIntoMatch(@PathParam("matchId") matchId: String,
                           newMatchPlayer: NewMatchPlayer): MatchInfo {
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