package kz.nurbayev.quralsdk.views

import android.app.Activity
import android.app.Dialog
import android.content.Context
import kz.nurbayev.quralsdk.models.QuralLoadingConfigure

class QuralProgressDialog(
    private val configure: QuralLoadingConfigure,
    ) {
    lateinit var dialog: CustomDialog

    fun show(context: Context): Dialog {
        return show(context, null)
    }

    private fun show(context: Context, title: CharSequence?): Dialog {
        val inflater = (context as Activity).layoutInflater
        val view = inflater.inflate(configure.layout, null)
        dialog = CustomDialog(context, configure.style, configure.backgroundColor)
        dialog.setContentView(view)
        dialog.show()
        return dialog
    }

    class CustomDialog(context: Context, style: Int, color: Int) : Dialog(context, style) {
        init {
            window?.decorView?.rootView?.setBackgroundResource(color)
            window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
                insets.consumeSystemWindowInsets()
            }
        }
    }
}