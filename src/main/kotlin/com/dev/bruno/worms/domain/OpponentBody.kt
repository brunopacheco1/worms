package com.dev.bruno.worms.domain

enum class OpponentBody {
    SOLID,
    GHOST;

    fun getEvaluator() {
            return when(this) {
                    SOLID -> SolidOpponentEvaluator()                                                GHOST -> GhostOpponentEvaluator()
            }                                                                        }
}
