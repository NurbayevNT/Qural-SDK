package kz.nurbayev.quralsdk.helpers

object StringUtils {

    fun isValid(string: String?): Boolean {
        return string != null && !string.isEmpty() && string != "null"
    }
}