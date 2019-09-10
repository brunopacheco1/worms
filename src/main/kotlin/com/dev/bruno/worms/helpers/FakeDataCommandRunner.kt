package com.dev.bruno.worms.helpers

import com.dev.bruno.worms.domain.*
import com.dev.bruno.worms.repositories.MatchRepository
import com.dev.bruno.worms.repositories.MatchPlayerRepository
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
    lateinit var matchPlayerRepository: MatchPlayerRepository

    fun onStart(@Observes ev: StartupEvent) {
        val player1 = Player("player_test_1")
        val player2 = Player("player_test_2")

        playerRepository.save(player1)
        playerRepository.save(player2)

        val runnningMatch = Match(
                Wall.SOLID,
                OpponentBody.SOLID,
                Difficulty.EASY,
                PlayMode.SURVIVAL,
                1,
                30
        )
        runnningMatch.status = MatchStatus.RUNNING

        matchRepository.save(runnningMatch)

        matchPlayerRepository.save(MatchPlayer(player1, runnningMatch))

        val notStartedMatch = Match(
                Wall.SOLID,
                OpponentBody.SOLID,
                Difficulty.EASY,
                PlayMode.SURVIVAL,
                1,
                30
        )
        matchRepository.save(notStartedMatch)
    }
}