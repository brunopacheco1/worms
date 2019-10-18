package com.dev.bruno.worms.exceptions

import io.netty.handler.codec.http.HttpResponseStatus

class MaximumPlayersException : WormsException(
        HttpResponseStatus.NOT_ACCEPTABLE.code(),
        "Maximum numbers of players has been reached for this match."
)