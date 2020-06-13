package com.core.data.base

import androidx.lifecycle.MutableLiveData
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference

open class BaseRepo(
    val sharedPreference: SharedPreference,
    val networkFactory: NetworkFactoryInterface
) {
    var exceptionMessage = MutableLiveData<Any>()
}
