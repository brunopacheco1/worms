package com.dev.bruno.worms.exceptions

open class WormsException(val statusCode: Int, override val message: String) : RuntimeException(message)