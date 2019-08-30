package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.PlayerAction
import com.google.common.collect.Maps
import java.util.concurrent.ConcurrentMap

object MatchPool {

    private val actions: ConcurrentMap<Long?, Direction> =
            Maps.newConcurrentMap()

    private val matches: ConcurrentMap<Long?, MutableList<MatchMap>> =
            Maps.newConcurrentMap()

    fun addPlayerAction(playerAction: PlayerAction) {
        actions[playerAction.playerId] = playerAction.direction
    }

    fun getLastMap(matchId: Long?): MatchMap? {
        return matches[matchId]?.lastOrNull()
    }

    fun addMap(currentMap: MatchMap) {
        val maps = matches[currentMap.matchId] ?: arrayListOf()
        maps.add(currentMap)
        matches[currentMap.matchId] = maps
    }

    fun getDirection(playerId: Long?): Direction? {
        return actions[playerId]
    }
}
