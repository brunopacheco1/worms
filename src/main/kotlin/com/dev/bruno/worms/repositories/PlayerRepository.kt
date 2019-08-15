package com.dev.bruno.worms.repositories

import com.dev.bruno.worms.domain.Player
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@ApplicationScoped
class PlayerRepository {

    @Inject
    private lateinit var em: EntityManager

    @Transactional
    fun save(player: Player): Player {
        val id = UUID.randomUUID().toString()
        player.id = id
        em.persist(player)
        return player
    }

    fun get(id: String) = em.find(Player::class.java, id)
}