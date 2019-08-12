package com.dev.bruno.worms.services

import com.dev.bruno.worms.dto.NewPlayer
import com.dev.bruno.worms.helpers.asPlayer
import com.dev.bruno.worms.helpers.asPlayerInfo
import com.dev.bruno.worms.repositories.PlayerRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class PlayerService {

    @Inject
    private lateinit var playerRepository: PlayerRepository

    fun addPlayer(newPlayer: NewPlayer) = playerRepository.save(newPlayer.asPlayer()).asPlayerInfo()
}