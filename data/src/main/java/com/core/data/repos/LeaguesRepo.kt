package com.core.data.repos

import androidx.lifecycle.LiveData
import com.core.data.MainExceptions
import com.core.data.base.BaseRepo
import com.core.data.model.leagues.LeaguesListResponseModel
import com.core.data.network.ApiFactory
import com.core.data.network.NetworkBoundFileResource
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference
import com.core.utils.FileManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

class LeaguesRepo(
    val apiFactory: ApiFactory,
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface,
    val fileManager: FileManager
) : BaseRepo(sharedPreferences, networkFactory) {
    fun requestLeaguesList(): LiveData<LeaguesListResponseModel> {
        networkFactory.setFileName("leagues_response.json")
        return object : NetworkBoundFileResource<LeaguesListResponseModel>(
            networkFactory,
            fileManager = null
        ) {
            override fun convert(json: String): LeaguesListResponseModel? {
                return Gson().fromJson(json, object : TypeToken<LeaguesListResponseModel>() {}.type)
            }

            override suspend fun createCall(): suspend () -> Response<LeaguesListResponseModel> = {
                apiFactory.getApisHelper().getLeaguesList().await()
            }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override fun handleErrorResponseType(response: Response<LeaguesListResponseModel>) {}
        }.asLiveData()
    }
}