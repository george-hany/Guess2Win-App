package com.core.network

import android.content.Context
import retrofit2.Response
import com.core.utils.CommonUtils
import com.core.network.model.Result

class MockedNetwork(var context: Context) :
    NetworkFactoryInterface {

    var assetFileName: String = ""
    override fun isNetworkConnected(): Boolean {
        return true
    }

    override suspend fun <T : Any> makeRequest(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Result<T>? {
        val response = Result.SuccessMocked(
            CommonUtils.loadJSONFromAsset(
                context,
                assetFileName
            )
        )
        return response as Result<T>
    }

    override fun setFileName(assetFileName: String) {
        this.assetFileName = assetFileName
    }

    override fun getStringJson(): String {
        return CommonUtils.loadJSONFromAsset(
            context,
            assetFileName
        )
    }
}
