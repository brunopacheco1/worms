package com.dev.bruno.worms.exceptions

import io.undertow.util.StatusCodes

class MatchRunningException : WormsException(
        StatusCodes.FORBIDDEN,
        "Match has started already."
)