package com.dev.bruno.worms.exceptions

import io.netty.handler.codec.http.HttpResponseStatus

class PlayerNotFoundException : WormsException(
        HttpResponseStatus.NOT_FOUND.code(),
        "Player not found."
)