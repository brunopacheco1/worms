package com.dev.bruno.worms.rest

import com.dev.bruno.worms.dto.MatchInfo
import com.dev.bruno.worms.dto.NewMatch
import com.dev.bruno.worms.dto.NewMatchPlayer
import com.dev.bruno.worms.services.MatchService
import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/v1/match")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
class MatchResource @Inject constructor(
        val matchService: MatchService
) {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun addMatch(newMatch: NewMatch) = matchService.addMatch(newMatch)

    @PUT
    @Path("/{id}/players")
    @Consumes(MediaType.APPLICATION_JSON)
    fun addPlayerIntoMatch(@PathParam("id") id: Long,
                           newMatchPlayer: NewMatchPlayer): MatchInfo {
        return matchService.addPlayerIntoMatch(id, newMatchPlayer)
    }

    @GET
    fun retrieveMatches(): List<MatchInfo> = matchService.retrieveMatches()

    @GET
    @Path("/{id}")
    fun getMatch(@PathParam("id") id: Long) = matchService.retrieveMatch(id)
}