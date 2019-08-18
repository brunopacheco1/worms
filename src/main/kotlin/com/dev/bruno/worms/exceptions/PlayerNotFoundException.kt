package com.dev.bruno.worms.exceptions

import io.undertow.util.StatusCodes

class PlayerNotFoundException : WormsException(
        StatusCodes.NOT_FOUND,
        "Player not found."
)