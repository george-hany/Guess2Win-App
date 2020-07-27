package com.core.data.repos

import androidx.lifecycle.LiveData
import com.core.data.MainExceptions
import com.core.data.base.BaseRepo
import com.core.data.model.matches.MatchesResponseModel
import com.core.data.network.ApiFactory
import com.core.data.network.NetworkBoundFileResource
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference
import com.core.utils.FileManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

class MatchesRepo(
    val apiFactory: ApiFactory,
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface,
    val fileManager: FileManager
) : BaseRepo(sharedPreferences, networkFactory) {
    fun requestMatchesByDate(date: String): LiveData<MatchesResponseModel> {
        networkFactory.setFileName("matches_response.json")
        return object : NetworkBoundFileResource<MatchesResponseModel>(
            networkFactory,
            fileManager = null
        ) {
            override fun convert(json: String): MatchesResponseModel? {
                return Gson().fromJson(json, object : TypeToken<MatchesResponseModel>() {}.type)
            }

            override suspend fun createCall(): suspend () -> Response<MatchesResponseModel> = {
                apiFactory.getApisHelper().getMatchesByDate(date).await()
            }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override fun handleErrorResponseType(response: Response<MatchesResponseModel>) {}
        }.asLiveData()
    }

    fun requestLeaguesMatchesByDate(
        date: String,
        leagueId: String
    ): LiveData<MatchesResponseModel> {
        this.networkFactory.setFileName("matches_response.json")
        return object : NetworkBoundFileResource<MatchesResponseModel>(
            networkFactory,
            fileManager = null
        ) {
            override fun convert(json: String): MatchesResponseModel? {
                return Gson().fromJson(json, object : TypeToken<MatchesResponseModel>() {}.type)
            }

            override suspend fun createCall(): suspend () -> Response<MatchesResponseModel> = {
                apiFactory.getApisHelper().getLeaguesMatchesByDate(date, leagueId).await()
            }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override fun handleErrorResponseType(response: Response<MatchesResponseModel>) {}
        }.asLiveData()
    }
}