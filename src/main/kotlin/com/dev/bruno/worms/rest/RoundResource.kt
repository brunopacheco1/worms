package com.dev.bruno.worms.rest

import com.dev.bruno.worms.dto.PlayerAction
import com.dev.bruno.worms.services.RoundService
import org.reactivestreams.Publisher
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
    @Path("/rounds")
    @Consumes(MediaType.APPLICATION_JSON)
    fun movingPlayer(@PathParam("id") id: Long,
                     playerAction: PlayerAction): Response {
        roundService.addAction(id, playerAction)
        return Response.accepted().build()
    }

    @GET
    @Path("/{id}/map")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    fun retrieveMap(@PathParam("id") id: Long
    ): Publisher<String> {
        return roundService.streamingMatchMap(id)
    }
}
