package com.core.network

import com.core.network.model.Result
import retrofit2.Response

interface NetworkFactoryInterface {
    fun isNetworkConnected(): Boolean
    suspend fun <T : Any> makeRequest(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Result<T>?

    fun setFileName(assetFileName: String) {}

    fun getStringJson(): String {
        return ""
    }
}
