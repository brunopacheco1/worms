package com.dev.bruno.worms.domain

import javax.persistence.Entity

@Entity
data class MapPoint(var x: Int, var y: Int) : Persistable<Long>()