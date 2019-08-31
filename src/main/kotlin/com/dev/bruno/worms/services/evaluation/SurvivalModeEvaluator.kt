package com.dev.bruno.worms.services.evaluation                                                                                                                   import com.dev.bruno.worms.domain.MatchStatus                                    import com.dev.bruno.worms.domain.PlayerStatus                                   import com.dev.bruno.worms.dto.MatchMap
import com.dev.bruno.worms.dto.RunningMatch
                                                                                 class SurvivalModeEvaluator : Evaluator() {                             
    override fun doEvaluation(runningMatch: RunningMatch,                                                      lastMap: MatchMap?,
                              currentMap: MatchMap) {
	if(lastMap != null && currentMap.status == FINISHED) {
		currentMap.players.foreachIndexed { index, player ->
			if(lastMap.players[index].status == PLAYING) {
				player.status = WINNER
			}
		}
	}
}
}
	
