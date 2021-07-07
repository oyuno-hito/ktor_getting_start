package com.example.controller

import io.ktor.application.call
import io.ktor.auth.*
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.put
import io.ktor.routing.delete

fun Route.helloWorldController() {
    route("/hello_world") {
        get {
            call.respondText { "Hello World!" }
        }
        authenticate("basic") {
            post {
                call.respond(HttpStatusCode.OK)
            }
        }
        put {
            call.respond(HttpStatusCode.BadRequest)
        }
        delete {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}
