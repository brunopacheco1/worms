package com.dev.bruno.worms.rest

import com.dev.bruno.worms.dto.PlayerAction
import com.dev.bruno.worms.services.MatchEvaluationService
import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import org.reactivestreams.Publisher

@Path("/api/v1/match")
@RequestScoped
class MatchEvaluationResource @Inject constructor(
    val matchEvaluationService: MatchEvaluationService
) {

    @PUT
    @Path("/{id}/rounds")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun movingPlayer(
        @PathParam("id") id: Long,
        playerAction: PlayerAction
    ): Response {
        matchEvaluationService.addAction(id, playerAction)
        return Response.accepted().build()
    }

    @GET
    @Path("/{id}/map")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    fun retrieveMap(
        @PathParam("id") id: Long
    ): Publisher<String> {
        return matchEvaluationService.streamingMatchMap(id)
    }
}
