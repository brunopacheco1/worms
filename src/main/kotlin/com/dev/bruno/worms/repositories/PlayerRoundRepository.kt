package com.dev.bruno.worms.repositories

import com.dev.bruno.worms.domain.PlayerMatch
import com.dev.bruno.worms.domain.PlayerRound
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PlayerRoundRepository : Repository<Long, PlayerRound>(PlayerRound::class.java)