package com.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.core.data.base.BaseRepo

open class BaseViewModel<D : BaseRepo>(
    val dataRecources: D
) : ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    var message = MutableLiveData<Any>()

    override fun onCleared() {
        super.onCleared()
    }

    fun setIsLoading(boolean: Boolean) {
        isLoading.postValue(boolean)
    }
}
