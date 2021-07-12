package com.example

import com.example.controller.helloWorldController
import com.example.controller.scoreController
import com.example.exception.PathParameterException
import com.example.model.SampleSession
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.server.netty.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*
import java.io.File

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
        }
    }
    val auths = listOf(
        Pair("taro", "password"),
        Pair("hanako", "password")
    )
    install(Authentication) {
        basic(name = "basic") {
            realm = "Ktor Server"
            // TODO: DBから認証情報の取得
            validate { credentials ->
                val auth = auths.firstOrNull { auth -> credentials.name == auth.first }
                if (auth != null && credentials.name == auth.first && credentials.password == auth.second) UserIdPrincipal(auth.first) else null
            }
        }

        form(name = "form") {
            // 一意なデータのパラメータ名
            userParamName = "user"
            // パスワードのパラメータ名
            passwordParamName = "password"
            // 認証失敗時の処理
            // NOTE 下記ページ内の記法は古いっぽくてエラーが発生したため、よしなに修正
            // https://jp.ktor.work/servers/features/authentication/basic.html
            challenge{ call.respond(UnauthorizedResponse()) }
            validate { credentials ->
                val auth = auths.firstOrNull { auth -> credentials.name == auth.first }
                println(auth)
                if (auth != null && credentials.name == auth.first && credentials.password == auth.second) UserIdPrincipal("user") else null
            }
        }
    }
    install(Sessions) {

        // cookie<SampleSession>(
        //     "SESSION_FEATURE_SESSION_ID",
        //     // NOTE: ディレクトリにキャッシュ情報を保存する
        //     // TODO: redisにセッション情報を保存
        //     directorySessionStorage(File(".sessions"), cached = true)
        // ) {
        //     cookie.path = "/" // Specify cookie's path '/' so it can be used in the whole site
        // }
        cookie<SampleSession>("COOKIE_NAME")
    }

    install(StatusPages) {
        /**
         * パスパラメータの型不一致の場合の例外レスポンス
         */
        exception<PathParameterException> {
            call.respond(HttpStatusCode.BadRequest, "PathParameterFormatException")
        }
        /**
         * ハンドリングしていない例外レスポンス
         */
        exception<Throwable> { cause->
            call.respond(HttpStatusCode.InternalServerError, "$cause.message")
        }
    }

    routing {
        helloWorldController()
        scoreController()
    }
}
