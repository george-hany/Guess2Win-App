package com.core.data.repos

import androidx.lifecycle.LiveData
import com.core.data.MainExceptions
import com.core.data.base.BaseRepo
import com.core.data.model.rank.RankResponseModel
import com.core.data.network.ApiFactory
import com.core.data.network.NetworkBoundFileResource
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference
import com.core.utils.FileManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

class RankRepo(
    val apiFactory: ApiFactory,
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface,
    val fileManager: FileManager
) : BaseRepo(sharedPreferences, networkFactory) {
    fun requestRanksList(type: String, selectedNum: Int): LiveData<RankResponseModel> {
        this.networkFactory.setFileName("rank_response.json")
        return object : NetworkBoundFileResource<RankResponseModel>(
            networkFactory,
            fileManager = fileManager
        ) {
            override fun convert(json: String): RankResponseModel? {
                return Gson().fromJson(json, object : TypeToken<RankResponseModel>() {}.type)
            }

            override suspend fun createCall(): suspend () -> Response<RankResponseModel> = {
                apiFactory.getApisHelper().getRanks(type, selectedNum).await()
            }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override fun handleErrorResponseType(response: Response<RankResponseModel>) {}
        }.asLiveData()
    }
}