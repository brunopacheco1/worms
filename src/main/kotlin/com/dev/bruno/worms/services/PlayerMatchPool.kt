package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.dto.MatchMap
import com.google.common.collect.Maps
import java.util.concurrent.ConcurrentMap

object PlayerMatchPool {
    val playerActions: ConcurrentMap<Long?, Direction> = Maps.newConcurrentMap()
    val playerMatches: ConcurrentMap<Long?, MutableList<MatchMap>> = Maps.newConcurrentMap()
}