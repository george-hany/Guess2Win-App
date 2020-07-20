package com.core.data.repos

import androidx.lifecycle.LiveData
import com.core.data.MainExceptions
import com.core.data.base.BaseRepo
import com.core.data.model.matchDetails.MatchDetailsResponseModel
import com.core.data.model.matchDetails.MatchExpectationRequest
import com.core.data.model.matchDetails.MatchExpectationResponse
import com.core.data.network.ApiFactory
import com.core.data.network.NetworkBoundFileResource
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference
import com.core.utils.FileManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

class MatchDetailsRepo(
    val apiFactory: ApiFactory,
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface,
    val fileManager: FileManager
) : BaseRepo(sharedPreferences, networkFactory) {
    fun requestMatchDetails(matchId: String): LiveData<MatchDetailsResponseModel> {
        networkFactory.setFileName("match_details_response.json")
        return object : NetworkBoundFileResource<MatchDetailsResponseModel>(
            networkFactory,
            fileManager = null
        ) {
            override fun convert(json: String): MatchDetailsResponseModel? {
                return Gson().fromJson(
                    json,
                    object : TypeToken<MatchDetailsResponseModel>() {}.type
                )
            }

            override suspend fun createCall(): suspend () -> Response<MatchDetailsResponseModel> = {
                apiFactory.getApisHelper().getMatchDetails(matchId).await()
            }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override fun handleErrorResponseType(response: Response<MatchDetailsResponseModel>) {}
        }.asLiveData()
    }

    fun requestMatchExpectation(matchExpectationRequest: MatchExpectationRequest): LiveData<MatchExpectationResponse> {
        networkFactory.setFileName("match_expectation_response.json")
        return object : NetworkBoundFileResource<MatchExpectationResponse>(
            networkFactory,
            fileManager = null
        ) {
            override fun convert(json: String): MatchExpectationResponse? {
                return Gson().fromJson(json, object : TypeToken<MatchExpectationResponse>() {}.type)
            }

            override suspend fun createCall(): suspend () -> Response<MatchExpectationResponse> = {
                apiFactory.getApisHelper().sendMatchExpectation(matchExpectationRequest).await()
            }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override fun handleErrorResponseType(response: Response<MatchExpectationResponse>) {}
        }.asLiveData()
    }
}