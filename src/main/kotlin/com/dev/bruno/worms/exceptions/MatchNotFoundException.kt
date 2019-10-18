package com.dev.bruno.worms.exceptions

import io.netty.handler.codec.http.HttpResponseStatus

class MatchNotFoundException : WormsException(
        HttpResponseStatus.NOT_FOUND.code(),
        "Match not found."
)