package com.dev.bruno.worms.rest

import com.dev.bruno.worms.domain.Match
import com.dev.bruno.worms.domain.Player
import com.dev.bruno.worms.dto.NewMatch
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/v1/match")
class MatchResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createPlayer(@RequestBody newMatch: NewMatch): Match {
        throw RuntimeException("Not implemented")
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{matchId}/players/{playerId}")
    fun addPlayer(@PathParam("matchId") matchId: String, @PathParam("playerId") playerId: String): Match {
        throw RuntimeException("Not implemented")
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun retrieveMatches(): Set<Match> {
        throw RuntimeException("Not implemented")
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    fun retrieveMatch(@PathParam("id") id: String): Match {
        throw RuntimeException("Not implemented")
    }
}