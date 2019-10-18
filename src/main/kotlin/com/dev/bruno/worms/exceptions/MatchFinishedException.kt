package com.dev.bruno.worms.exceptions

import io.netty.handler.codec.http.HttpResponseStatus

class MatchFinishedException : WormsException(
        HttpResponseStatus.FORBIDDEN.code(),
        "Match has finished already."
)