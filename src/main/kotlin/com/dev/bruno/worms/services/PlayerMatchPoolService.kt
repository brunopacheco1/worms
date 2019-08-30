package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.dto.PlayerAction
import com.dev.bruno.worms.dto.MatchMap
import com.google.common.collect.Maps
import java.util.concurrent.ConcurrentMap
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
object PlayerMatchPoolService {

    private val actions: ConcurrentMap<Long?, Direction> =
        Maps.newConcurrentMap()

    private val matches: ConcurrentMap<Long?, MutableList<MatchMap>> =
        Maps.newConcurrentMap()

    fun addPlayerAction(playerAction: PlayerAction) {
        actions[playerAction.playerId] = playerAction.direction
    }

    fun getLastMap(matchId: Long): MatchMap {
        return matches[matchId].last()
    }

    fun getMaps(matchId: Long)
}
