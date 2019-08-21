package com.dev.bruno.worms.domain

import java.io.Serializable
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class Persistable<T : Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: T? = null
}