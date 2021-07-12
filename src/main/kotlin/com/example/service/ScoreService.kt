package com.example.service

import com.example.exception.PathParameterException
import com.example.response.ScoreDetailResponse
import com.example.validator.ScoreValidator
import io.ktor.http.*

class ScoreService {
    // TODO: DIの利用
    private val scoreValidator = ScoreValidator()
    fun list(): List<ScoreDetailResponse> {
        return listOf(
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
    }

    fun show(id: String?): ScoreDetailResponse {
        if(!scoreValidator.userIdValidator(id)){
            throw PathParameterException()
        }

        val userId = id!!.toInt()
        return ScoreDetailResponse(
            id = userId,
            name = "taro",
            math = 100,
            science = 100,
            japanese = 100,
            english = 100
        )
    }

    fun create(id: String?): HttpStatusCode {
        if(!scoreValidator.userIdValidator(id)){
            throw PathParameterException()
        }
        return HttpStatusCode.OK
    }

    fun update(id: String?): HttpStatusCode {
        if(!scoreValidator.userIdValidator(id)){
            throw PathParameterException()
        }
        return HttpStatusCode.OK
    }

    fun delete(id: String?): HttpStatusCode {
        if(!scoreValidator.userIdValidator(id)){
            throw PathParameterException()
        }
        return HttpStatusCode.OK
    }

}
