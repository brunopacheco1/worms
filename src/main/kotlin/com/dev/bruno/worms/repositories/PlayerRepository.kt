package com.dev.bruno.worms.repositories

import com.dev.bruno.worms.domain.Player
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PlayerRepository : Repository<Player>(Player::class.java)