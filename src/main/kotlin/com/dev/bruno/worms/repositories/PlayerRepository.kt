package com.dev.bruno.worms.repositories

import com.dev.bruno.worms.domain.Player
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PlayerRepository : Repository<Long, Player>(Player::class.java)