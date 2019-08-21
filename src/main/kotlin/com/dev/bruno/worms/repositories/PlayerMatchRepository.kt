package com.dev.bruno.worms.repositories

import com.dev.bruno.worms.domain.PlayerMatch
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PlayerMatchRepository : Repository<Long, PlayerMatch>(PlayerMatch::class.java)