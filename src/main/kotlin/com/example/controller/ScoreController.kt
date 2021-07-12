package com.example.controller

import com.example.service.ScoreService
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.scoreController() {
    // TODO: DIの利用(Koin or Dagger?)
    val scoreService = ScoreService()

    route("/score") {
        /**
         * 成績一覧取得API
         * ScoreDetailResponseのリストを返却する
         */
        get {
            call.respond(
                scoreService.list()
            )
        }
        route("/{user_id}") {
            /**
             * 成績詳細取得API
             * ScoreDetailResponseを返却する
             */
            get {
                val userId = call.parameters["user_id"]
                call.respond(
                    scoreService.show(userId)
                )
            }
            /**
             * 成績登録API
             */
            post {
                val userId = call.parameters["user_id"]
                call.respond(
                    scoreService.create(userId)
                )
            }
            /**
             * 成績更新API
             */
            put {
                val userId = call.parameters["user_id"]
                call.respond(
                    scoreService.update(userId)
                )
            }
            /**
             * 成績削除API
             */
            delete {
                val userId = call.parameters["user_id"]
                call.respond(
                    scoreService.delete(userId)
                )
            }
        }

    }
}
