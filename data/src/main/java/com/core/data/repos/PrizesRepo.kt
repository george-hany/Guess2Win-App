package com.core.data.repos

import androidx.lifecycle.LiveData
import com.core.data.MainExceptions
import com.core.data.base.BaseRepo
import com.core.data.model.prizes.PrizesResponse
import com.core.data.network.ApiFactory
import com.core.data.network.NetworkBoundFileResource
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference
import com.core.utils.FileManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

class PrizesRepo(
    val apiFactory: ApiFactory,
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface,
    val fileManager: FileManager
) : BaseRepo(sharedPreferences, networkFactory) {
    fun getPrizes(): LiveData<PrizesResponse> {
        networkFactory.setFileName("prizes_response.json")
        return object : NetworkBoundFileResource<PrizesResponse>(
            networkFactory,
            fileManager = null
        ) {
            override fun convert(json: String): PrizesResponse? {
                return Gson().fromJson(json, object : TypeToken<PrizesResponse>() {}.type)
            }

            override suspend fun createCall(): suspend () -> Response<PrizesResponse> = {
                apiFactory.getApisHelper().getPrizes().await()
            }

            override fun handleErrorResponseType(response: Response<PrizesResponse>) {}

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }
        }.asLiveData()
    }
}