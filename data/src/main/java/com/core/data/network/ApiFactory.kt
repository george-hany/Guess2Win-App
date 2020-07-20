package com.core.data.network

import com.core.data.BuildConfig
import com.core.data.constant.AppConstant
import com.core.network.RetrofitClient
import xware.core.data.network.interfaces.ApisHelper

class ApiFactory(var appConstant: AppConstant) {

    fun getApisHelper(): ApisHelper {
        return RetrofitClient
            .retrofit(BuildConfig.BASE_URL + "api/v1/")
            .create(ApisHelper::class.java)
    }
}
