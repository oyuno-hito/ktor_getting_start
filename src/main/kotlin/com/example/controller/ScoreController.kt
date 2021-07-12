package com.example.controller

import com.example.response.ScoreDetailResponse
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.lang.NumberFormatException

fun Route.scoreController() {
    route("/score") {
        /**
         * 成績一覧取得API
         */
        get {
            call.respond(
                listOf(
                    ScoreDetailResponse(
                        id = 1,
                        name = "taro",
                        math = 100,
                        science = 100,
                        japanese = 100,
                        english = 100
                    ),
                    ScoreDetailResponse(
                        id = 2,
                        name = "jiro",
                        math = 0,
                        science = 0,
                        japanese = 0,
                        english = 0
                    )
                )
            )
        }
        route("/{user_id}") {
            /**
             * 成績詳細取得API
             */
            get {
                try {
                    val userId = call.parameters["user_id"]?.toInt()
                    userId?.let {
                        call.respond(
                            ScoreDetailResponse(
                                id = it,
                                name = "taro",
                                math = 100,
                                science = 100,
                                japanese = 100,
                                english = 100
                            )
                        )
                    }

                } catch (e: NumberFormatException) {
                    call.respond(HttpStatusCode.BadRequest, "NumberFormatException")
                }
            }
            /**
             * 成績登録API
             */
            post {
                call.respond(HttpStatusCode.OK)
            }
            /**
             * 成績更新API
             */
            put {
                val userId = call.parameters["user_id"]
                call.respond(HttpStatusCode.OK)
            }
            /**
             * 成績削除API
             */
            delete {
                val userId = call.parameters["user_id"]
                call.respond(HttpStatusCode.OK)
            }
        }

    }
}
