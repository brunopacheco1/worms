package com.dev.bruno.worms.services.evaluation

import com.dev.bruno.worms.domain.MatchStatus
import com.dev.bruno.worms.domain.PlayerRoundStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch

class LongestWormModeEvaluator : Evaluator() {
    override fun doEvaluation(runningMatch: RunningMatch, lastMap: MatchMap?,
                              currentMap: MatchMap) {
        if (currentMap.status == MatchStatus.FINISHED) {
            val player = currentMap.players.maxBy { it.wormLength }
            player?.status = PlayerRoundStatus.WINNER
        }
    }
}
	
