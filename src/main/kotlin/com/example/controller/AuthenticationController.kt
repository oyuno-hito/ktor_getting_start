package com.example.controller

import com.example.model.SampleSession
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*

fun Route.authenticationController() {
    route("/login") {
        authenticate("form") {
            post {
                val principal: UserIdPrincipal? = call.authentication.principal()
                if (principal?.name != null) {
                    call.sessions.set(SampleSession(name = principal.name, id = 1, value = 0))
                }
                call.respond(HttpStatusCode.OK)
            }
        }
    }
    route("/logout") {
        get {
            call.sessions.clear<SampleSession>()
            call.respond(HttpStatusCode.OK)
        }
    }
}
