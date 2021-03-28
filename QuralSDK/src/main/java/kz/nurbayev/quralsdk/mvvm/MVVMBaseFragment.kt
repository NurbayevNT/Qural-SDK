package kz.nurbayev.quralsdk.mvvm

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.qural_lottie_dialog.view.*
import kz.nurbayev.quralsdk.R
import kz.nurbayev.quralsdk.models.NavigationConfigure
import kz.nurbayev.quralsdk.models.QuralLoadingConfigure
import kz.nurbayev.quralsdk.services.VibrateService
import kz.nurbayev.quralsdk.views.QuralProgressDialog
import org.jetbrains.anko.sdk27.coroutines.onClick

class MVVMBaseFragment(
    private val layout: Int,
    private val loadingConfigure: QuralLoadingConfigure =
        QuralLoadingConfigure(
            R.layout.qural_loading_dialog,
            R.style.CustomDialogTheme,
            R.color.white
        ),
    private val navHostConfigure: NavigationConfigure? = null

) : Fragment() {

    private val progressDialog = QuralProgressDialog(loadingConfigure)
    private var isAnyDialogActive = false

    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(layout, container, false)
        return root
    }

    fun navigateTo(navDirections: Int) {
        when (navHostConfigure != null) {
            true -> {
                hideLoading()
                requireActivity().findNavController(navHostConfigure.containerId)
                    .navigate(navDirections)
            }
        }
    }

    fun showLoading() {
        when (isAnyDialogActive) {
            false -> {
                hideLoading()
                isAnyDialogActive = true
                progressDialog.show(requireContext())
            }
        }
    }

    fun hideLoading() {
        when (isAnyDialogActive) {
            true -> progressDialog.dialog.dismiss()
        }
    }

    fun showLottieAlert(message: String, actionButtonTitle: String? = null) {
        hideLoading()
        activateVibrate()
        val mDialogView = LayoutInflater.from(requireContext()).inflate(
            R.layout.qural_lottie_dialog,
            null
        )
        val mBuilder = AlertDialog.Builder(requireContext())
            .setView(mDialogView)
            .setCancelable(true)
        val mAlertDialog = mBuilder.show()
        mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialogView.qural_dialog_message.text = message
        mDialogView.qural_dialog_close_button.text = actionButtonTitle
        mDialogView.qural_dialog_close_button.onClick {
            mAlertDialog.dismiss()
        }
    }


    fun showLongToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    fun showShortToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


    private fun activateVibrate() {
        val intentVibrate = Intent(
            activity?.applicationContext,
            VibrateService::class.java
        )
        activity?.startService(intentVibrate)
    }

}