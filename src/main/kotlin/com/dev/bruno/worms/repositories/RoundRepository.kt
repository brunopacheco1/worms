package com.dev.bruno.worms.repositories

import com.dev.bruno.worms.domain.Round
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class RoundRepository : Repository<Long, Round>(Round::class.java)