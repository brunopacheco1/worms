package com.dev.bruno.worms.repositories

import com.dev.bruno.worms.domain.Match
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MatchRepository : Repository<Long, Match>(Match::class.java)