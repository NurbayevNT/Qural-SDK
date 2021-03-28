package kz.nurbayev.quralsdk.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kz.nurbayev.quralsdk.utils.HandleErrorUtil
import retrofit2.Response

class MVVMBaseViewModel(application: Application): AndroidViewModel(application) {

    val isError: MutableLiveData<String?> = MutableLiveData()
    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    private fun <T> handleErrorBody(response: Response<T>) {
        isError.postValue(HandleErrorUtil.handleErrorMessage(response))
    }
}