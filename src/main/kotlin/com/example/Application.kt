package com.example

import com.example.controller.helloWorldController
import io.ktor.server.netty.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Authentication) {
        basic(name = "basic") {
            realm = "Ktor Server"
            // TODO: DBから認証情報の取得
            validate { if (it.name == "user" && it.password == "password") UserIdPrincipal("user") else null }
        }
    }

    routing {
        helloWorldController()
    }
}
