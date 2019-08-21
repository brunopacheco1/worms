package com.dev.bruno.worms.domain

import javax.persistence.Entity

@Entity
data class MapPoint(val x: Int, val y: Int) : Persistable<Long>()