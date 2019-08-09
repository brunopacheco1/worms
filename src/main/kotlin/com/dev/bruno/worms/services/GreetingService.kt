package com.dev.bruno.worms.services

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GreetingService {

    fun greeting(name: String) = "hello $name"
}