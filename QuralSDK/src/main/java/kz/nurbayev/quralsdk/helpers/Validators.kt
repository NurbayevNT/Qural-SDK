package kz.nurbayev.quralsdk.helpers

import java.util.regex.Pattern

object Validators {
    private val CORRECT_LOGIN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"
    )

    fun validateLogin(login: String): Boolean {
        return when {
            login.isBlank() -> false
            !CORRECT_LOGIN.matcher(login).matches() -> false
            else -> true
        }
    }

    fun validatePasswordLength(password: String, length: Int): Boolean {
        return when {
            password.isBlank() -> false
            password.length < length -> false
            else -> true
        }
    }

}