package com.dev.bruno.worms.exceptions

import com.dev.bruno.worms.dto.ExceptionResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class WormsExceptionHandler : ExceptionMapper<WormsException> {

    val log: Logger = LoggerFactory.getLogger(WormsExceptionHandler::class.java)

    override fun toResponse(ex: WormsException): Response {
        log.info(ex.message, ex)
        return Response.status(ex.statusCode).entity(ExceptionResponse(ex.message)).build()
    }
}