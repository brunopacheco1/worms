package com.dev.bruno.worms.exceptions

import io.undertow.util.StatusCodes

class MaximumPlayersException : WormsException(
        StatusCodes.NOT_ACCEPTABLE,
        "Maximum numbers of players has been reached for this match."
)