package com.dev.bruno.worms.repositories

import com.dev.bruno.worms.domain.Player
import javax.enterprise.context.ApplicationScoped
import javax.persistence.NoResultException

@ApplicationScoped
class PlayerRepository : Repository<Long, Player>(Player::class.java) {

    fun findByNickname(nickname: String): Player? {
        try {
            return em.createQuery("select p from PLAYER p where p.nickname = :nickname", Player::class.java)
                    .setParameter("nickname", nickname).singleResult
        } catch (e: NoResultException) {
            return null
        }
    }
}