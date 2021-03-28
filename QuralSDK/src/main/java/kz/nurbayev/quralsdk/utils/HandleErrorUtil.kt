package kz.nurbayev.quralsdk.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kz.nurbayev.quralsdk.models.ExceptionModel
import retrofit2.Response

object HandleErrorUtil {
    fun <T> handleErrorMessage(response: Response<T>): String{
        val type = object : TypeToken<ExceptionModel>() {}.type
        val errorBody: ExceptionModel = Gson().fromJson(response.errorBody()!!.charStream(), type)
        return errorBody.message
    }
}