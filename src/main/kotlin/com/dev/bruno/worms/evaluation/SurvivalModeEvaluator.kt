package com.dev.bruno.worms.evaluation

import com.dev.bruno.worms.domain.MatchStatus
import com.dev.bruno.worms.domain.MatchPlayerStatus
import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch

class SurvivalModeEvaluator : Evaluator() {

    override fun doEvaluation(runningMatch: RunningMatch,
                              lastMap: MatchMap?,
                              currentMap: MatchMap) {
        if (lastMap != null && currentMap.status == MatchStatus.FINISHED) {
            currentMap.players.forEachIndexed { index, player ->
                if (lastMap.players[index].status == MatchPlayerStatus.PLAYING) {
                    player.status = MatchPlayerStatus.WINNER
                }
            }
        }
    }
}
	
