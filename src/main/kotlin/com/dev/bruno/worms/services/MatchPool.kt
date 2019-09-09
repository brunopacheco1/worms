package com.dev.bruno.worms.services

import com.dev.bruno.worms.domain.Direction
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.PlayerAction
import com.dev.bruno.worms.dto.RunningMatch
import com.google.common.collect.Maps
import java.util.concurrent.ConcurrentMap

object MatchPool {

    private val ACTIONS: ConcurrentMap<Long, Direction> =
            Maps.newConcurrentMap()

    private val MAPS: ConcurrentMap<Long, MatchMap> =
            Maps.newConcurrentMap()

    fun addAction(playerAction: PlayerAction) {
        ACTIONS[playerAction.playerId] = playerAction.direction
    }

    fun getLastMap(matchId: Long): MatchMap? {
        return MAPS[matchId]
    }

    fun addMap(map: MatchMap) {
        this.MAPS[map.matchId] = map
    }

    fun getDirection(playerId: Long): Direction? {
        return ACTIONS[playerId]
    }

    fun clearMapAndAction(match: RunningMatch) {
        match.players.forEach { ACTIONS.remove(it) }
        MAPS.remove(match.id)
    }
}
