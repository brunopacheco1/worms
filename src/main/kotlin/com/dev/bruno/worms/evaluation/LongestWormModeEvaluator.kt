package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.MatchStatus
import com.dev.bruno.worms.domain.MatchPlayerStatus
import com.dev.bruno.worms.dto.Map
import com.dev.bruno.worms.dto.RunningMatch

class LongestWormModeEvaluator : Evaluator() {
    override fun doEvaluation(runningMatch: RunningMatch, lastMap: Map?,
                              currentMap: Map) {
        if (currentMap.status == MatchStatus.FINISHED) {
            val player = currentMap.players.maxBy { it.wormLength }
            player?.status = MatchPlayerStatus.WINNER
        }
    }
}
	
