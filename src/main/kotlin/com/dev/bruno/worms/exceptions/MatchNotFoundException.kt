package com.dev.bruno.worms.exceptions

import io.undertow.util.StatusCodes

class MatchNotFoundException : WormsException(
        StatusCodes.NOT_FOUND,
        "Match not found."
)