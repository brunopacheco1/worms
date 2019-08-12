package com.dev.bruno.worms.helpers

import com.fasterxml.jackson.databind.ObjectMapper

fun Any.toJson() = ObjectMapper().writeValueAsString(this)