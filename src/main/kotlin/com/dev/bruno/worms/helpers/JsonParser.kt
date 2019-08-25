package com.dev.bruno.worms.helpers

import com.fasterxml.jackson.databind.ObjectMapper

fun Any.toJson(): String = ObjectMapper().writeValueAsString(this)
fun <T> String.fromJson(clazz: Class<T>): T = ObjectMapper().readValue(this, clazz)