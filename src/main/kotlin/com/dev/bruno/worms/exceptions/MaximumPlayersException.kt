package com.dev.bruno.worms.exceptions

import io.undertow.util.StatusCodes

class MaximumPlayersException : WormsException(
        StatusCodes.NOT_ACCEPTABLE,
        "Maximum numbers of players for this match has been reached."
)