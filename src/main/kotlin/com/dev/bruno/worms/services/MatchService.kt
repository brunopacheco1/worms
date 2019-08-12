package com.dev.bruno.worms.services

import com.dev.bruno.worms.dto.NewMatch
import com.dev.bruno.worms.helpers.asMatch
import com.dev.bruno.worms.helpers.asMatchInfo
import com.dev.bruno.worms.repositories.MatchRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class MatchService {

    @Inject
    lateinit var repository: MatchRepository

    fun add(newMatch: NewMatch) = repository.save(newMatch.asMatch()).asMatchInfo()
}