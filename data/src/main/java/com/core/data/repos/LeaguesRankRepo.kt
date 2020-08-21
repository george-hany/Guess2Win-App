package com.core.data.repos

import androidx.lifecycle.LiveData
import com.core.data.MainExceptions
import com.core.data.base.BaseRepo
import com.core.data.constant.SharedPrefKeys.Companion.LOGIN_DATA
import com.core.data.model.leaguesRank.LeaguesRankResponseModel
import com.core.data.model.login.LoginResponse
import com.core.data.network.ApiFactory
import com.core.data.network.NetworkBoundFileResource
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference
import com.core.utils.FileManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

class LeaguesRankRepo(
    val apiFactory: ApiFactory,
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface,
    val fileManager: FileManager
) : BaseRepo(sharedPreferences, networkFactory) {
    fun requestLeaguesRankList(leagueId: String): LiveData<List<LeaguesRankResponseModel>> {
//        this.networkFactory.setFileName("leagues_rank_response.json")
        return object : NetworkBoundFileResource<List<LeaguesRankResponseModel>>(
            networkFactory,
            fileName = "leagues_rank_response$leagueId.json",
            fileManager = fileManager
        ) {
            override fun convert(json: String): List<LeaguesRankResponseModel>? {
                return Gson().fromJson(
                    json,
                    object : TypeToken<List<LeaguesRankResponseModel>>() {}.type
                )
            }

            override suspend fun createCall(): suspend () -> Response<List<LeaguesRankResponseModel>> = {
                    apiFactory.getApisHelper().getLeaguesRank(leagueId).await()
                }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override fun handleErrorResponseType(response: Response<List<LeaguesRankResponseModel>>) {}
        }.asLiveData()
    }

    fun getLoginResponseFromSharedPref(): LoginResponse = Gson().fromJson(
        sharedPreference.getString(LOGIN_DATA),
        object : TypeToken<LoginResponse>() {}.type
    )
}