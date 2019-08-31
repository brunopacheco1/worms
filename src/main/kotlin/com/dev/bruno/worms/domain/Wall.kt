package com.dev.bruno.worms.domain

enum class Wall {
    SOLID,
    MIRROR;

    fun getEvaluator() {
	    return when(this) {
		    SOLID -> SolidWallEvaluator()
		    MIRROR -> MirrorWallEvaluator()
	    }
    }
}
