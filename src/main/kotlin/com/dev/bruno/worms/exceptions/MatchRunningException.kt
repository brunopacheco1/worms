package com.dev.bruno.worms.exceptions

import io.netty.handler.codec.http.HttpResponseStatus

class MatchRunningException : WormsException(
        HttpResponseStatus.FORBIDDEN.code(),
        "Match has started already."
)