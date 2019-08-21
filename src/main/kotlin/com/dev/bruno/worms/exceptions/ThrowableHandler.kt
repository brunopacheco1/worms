package com.dev.bruno.worms.exceptions

import com.dev.bruno.worms.dto.ExceptionResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ThrowableHandler : ExceptionMapper<Throwable> {

    val log: Logger = LoggerFactory.getLogger(ThrowableHandler::class.java)

    override fun toResponse(ex: Throwable): Response {
        log.error(ex.message, ex)
        return Response.status(500).entity(ExceptionResponse("[Unknown exception] ${ex.message}")).build()
    }
}