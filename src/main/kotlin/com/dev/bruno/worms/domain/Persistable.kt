package com.dev.bruno.worms.domain

import java.io.Serializable
import javax.persistence.*

@MappedSuperclass
abstract class Persistable<T : Serializable> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: T? = null
}