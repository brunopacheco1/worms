package com.dev.bruno.worms.repositories

import com.dev.bruno.worms.domain.Persistable
import java.io.Serializable
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

abstract class Repository<K : Serializable, T : Persistable<K>>(open val clazz: Class<T>) {

    @PersistenceContext
    protected open lateinit var em: EntityManager

    @Transactional
    open fun save(entity: T): T {
        em.persist(entity)
        return entity
    }

    @Transactional
    open fun update(entity: T): T {
        return em.merge(entity)
    }

    open fun get(id: K): T? = em.find(clazz, id)

    open fun list() = em.createQuery("Select e from ${clazz.name} e", clazz).resultList
}