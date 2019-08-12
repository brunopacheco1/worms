package com.dev.bruno.worms.repositories

import com.dev.bruno.worms.domain.Player
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PlayerRepository {

    val playerMap: HashMap<String, Player> = hashMapOf()

    fun save(player: Player): Player {
        val id = UUID.randomUUID().toString()
        player.id = id
        playerMap[player.id] = player
        return player
    }
}