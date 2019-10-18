package com.dev.bruno.worms.exceptions

import io.netty.handler.codec.http.HttpResponseStatus

class MatchNotStartedException : WormsException(
        HttpResponseStatus.FORBIDDEN.code(),
        "Match hasn't started yet."
)