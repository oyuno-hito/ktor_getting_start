package com.example

import com.example.controller.helloWorldController
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import io.ktor.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureRouting()
        configureSecurity()
        routing {
            helloWorldController()
        }
    }.start(wait = true)
}
