package com.dev.bruno.worms.exceptions

import io.undertow.util.StatusCodes

class MatchFinishedException : WormsException(
        StatusCodes.FORBIDDEN,
        "Match has finished already."
)