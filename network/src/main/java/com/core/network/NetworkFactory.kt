package com.core.network

import android.content.Context
import android.net.ConnectivityManager
import com.core.network.model.Result
import retrofit2.Response

class NetworkFactory(var context: Context) :
    NetworkFactoryInterface {

    override fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    override suspend fun <T : Any> makeRequest(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Result<T>? {
        return safeApiResult(call, errorMessage)
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Result<T> {
        val response = call.invoke()
        if (response.isSuccessful) return Result.Success(response)
        return Result.Error(response)
    }
}
