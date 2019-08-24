package com.dev.bruno.worms.domain

import javax.persistence.Embeddable

@Embeddable
data class MapPoint(var x: Int, var y: Int)