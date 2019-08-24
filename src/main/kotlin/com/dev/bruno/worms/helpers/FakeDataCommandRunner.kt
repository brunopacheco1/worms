package com.dev.bruno.worms.helpers

import com.dev.bruno.worms.domain.*
import com.dev.bruno.worms.repositories.MatchRepository
import com.dev.bruno.worms.repositories.PlayerMatchRepository
import com.dev.bruno.worms.repositories.PlayerRepository
import io.quarkus.runtime.StartupEvent
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes
import javax.inject.Inject

@ApplicationScoped
class FakeDataCommandRunner {

    @Inject
    lateinit var playerRepository: PlayerRepository

    @Inject
    lateinit var matchRepository: MatchRepository

    @Inject
    lateinit var playerMatchRepository: PlayerMatchRepository

    fun onStart(@Observes ev: StartupEvent) {
        val player1 = Player("player_test_1")
        val player2 = Player("player_test_2")

        playerRepository.save(player1)
        playerRepository.save(player2)

        val match = Match(
                GameMode.CLASSIC,
                GamePlay.ARROWS,
                Difficulty.EASY,
                PlayerMode.SOLID,
                1,
                30
        )
        match.status = MatchStatus.RUNNING

        matchRepository.save(match)

        playerMatchRepository.save(PlayerMatch(player1, match))
    }
}