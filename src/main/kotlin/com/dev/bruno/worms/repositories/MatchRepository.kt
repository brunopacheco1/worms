package com.dev.bruno.worms.repositories

import com.dev.bruno.worms.domain.Match
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

@ApplicationScoped
class MatchRepository {

    @Inject
    lateinit var em: EntityManager

    @Transactional
    fun save(match: Match): Match {
        val id = UUID.randomUUID().toString()
        match.id = id
        em.persist(match)
        return match
    }
}