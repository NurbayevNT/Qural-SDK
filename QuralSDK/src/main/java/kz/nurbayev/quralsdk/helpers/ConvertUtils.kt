package kz.nurbayev.quralsdk.helpers

import android.content.Context
import android.util.DisplayMetrics
import kotlin.math.roundToInt

object ConvertUtils {
    fun millisToSeconds(millis: Long): Int {
        return (millis / 1000.0f).roundToInt()
    }

    fun secondsToMinutes(seconds: Long): Int {
        return (seconds / 60.0f).roundToInt()
    }

    fun metersToKilometers(meters: Double): Int {
        return (meters / 1000.0).roundToInt().toInt()
    }

    fun dpToPx(dp: Float, context: Context): Float {
        return dp * (context.resources
            .displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun pxToDp(px: Int, context: Context): Float {
        return px / (context.resources
            .displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}