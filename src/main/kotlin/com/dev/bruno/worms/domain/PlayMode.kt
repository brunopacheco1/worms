enum class PlayMode {
	SURVIVAL,
	LONGEST_WORM;

	fun getEvaluator() {
            return when(this) {
                    SURVIVAL -> SurvivalModeEvaluator()                                              LONGEST_WORM -> LongestWormEvaluator()
            }                                                                        }
    }
