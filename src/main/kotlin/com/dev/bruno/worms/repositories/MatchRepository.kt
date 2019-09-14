package com.dev.bruno.worms.repositories

import com.dev.bruno.worms.domain.Match
import com.dev.bruno.worms.domain.MatchStatus
import javax.enterprise.context.ApplicationScoped


@ApplicationScoped
class MatchRepository : Repository<Long, Match>(Match::class.java) {

    fun findNotStartedMatch(): Match? {
        return em.createQuery("select m from MATCH m where m.status = :status", Match::class.java)
                .setParameter("status", MatchStatus.WAITING_PLAYERS).resultList.firstOrNull()
    }
}