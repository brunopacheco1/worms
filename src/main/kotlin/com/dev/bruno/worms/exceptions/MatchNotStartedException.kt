package com.dev.bruno.worms.exceptions

import io.undertow.util.StatusCodes

class MatchNotStartedException : WormsException(
        StatusCodes.FORBIDDEN,
        "Match hasn't started yet."
)