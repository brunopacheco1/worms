package com.dev.bruno.worms.repositories

import com.dev.bruno.worms.domain.MatchPlayer
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MatchPlayerRepository : Repository<Long, MatchPlayer>(MatchPlayer::class.java)