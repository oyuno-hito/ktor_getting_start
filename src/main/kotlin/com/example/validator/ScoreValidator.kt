package com.example.validator

import java.lang.NumberFormatException

class ScoreValidator {
    fun userIdValidator(userId: String?): Boolean {
        if (userId == null) {
            return false
        }
        return try {
            userId.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
}
