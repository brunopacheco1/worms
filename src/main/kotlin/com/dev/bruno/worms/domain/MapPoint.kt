package com.dev.bruno.worms.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class MapPoint(
        @Id
        @GeneratedValue
        val id: Long,
        val x: Int,
        val y: Int
)