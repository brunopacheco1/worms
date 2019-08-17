package com.dev.bruno.worms.exceptions

class MaximumPlayersException : WormsException(
        400,
        "Maximum numbers of players for this match has been reached"
)