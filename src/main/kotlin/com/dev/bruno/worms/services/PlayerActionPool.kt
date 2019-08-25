package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.Direction
import com.google.common.collect.Maps
import java.util.concurrent.ConcurrentMap

object PlayerActionPool {
    val playerActions: ConcurrentMap<Long, Direction> = Maps.newConcurrentMap()
}