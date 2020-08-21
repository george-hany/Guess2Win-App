package com.core.data.repos

import androidx.lifecycle.LiveData
import com.core.data.MainExceptions
import com.core.data.base.BaseRepo
import com.core.data.constant.SharedPrefKeys
import com.core.data.model.extraPoints.CheckWatchAdAvailabilityResponseModel
import com.core.data.model.extraPoints.ConfirmWatchingAdResponseModel
import com.core.data.model.login.LoginResponse
import com.core.data.network.ApiFactory
import com.core.data.network.NetworkBoundFileResource
import com.core.data.strategy.DataStrategy
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference
import com.core.utils.FileManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

class ExtraPointsRepo(
    val apiFactory: ApiFactory,
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface,
    val fileManager: FileManager
) : BaseRepo(sharedPreferences, networkFactory) {
    fun requestRewardAdAvailability(): LiveData<CheckWatchAdAvailabilityResponseModel> {
        return object : NetworkBoundFileResource<CheckWatchAdAvailabilityResponseModel>(
            networkFactory,
            DataStrategy.Strategies.NETWORK_ONLY,
            fileManager = fileManager
        ) {
            override fun convert(json: String): CheckWatchAdAvailabilityResponseModel? {
                return Gson().fromJson(json, object : TypeToken<CheckWatchAdAvailabilityResponseModel>() {}.type)
            }

            override suspend fun createCall(): suspend () -> Response<CheckWatchAdAvailabilityResponseModel> = {
                apiFactory.getApisHelper()
                    .checkRewardAdAvailability(getLoginResponse().user?.id ?: "").await()
            }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override fun handleErrorResponseType(response: Response<CheckWatchAdAvailabilityResponseModel>) {
                exceptionMessage.value = response.message()
            }
        }.asLiveData()
    }

    fun getLoginResponse(): LoginResponse = Gson().fromJson(
        sharedPreference.getString(SharedPrefKeys.LOGIN_DATA),
        object : TypeToken<LoginResponse>() {}.type
    )

    fun confirmWatchingAd(): LiveData<ConfirmWatchingAdResponseModel> {
        return object : NetworkBoundFileResource<ConfirmWatchingAdResponseModel>(
            networkFactory,
            DataStrategy.Strategies.NETWORK_ONLY,
            fileManager = fileManager
        ) {
            override fun convert(json: String): ConfirmWatchingAdResponseModel? {
                return Gson().fromJson(json, object : TypeToken<ConfirmWatchingAdResponseModel>() {}.type)
            }

            override suspend fun createCall(): suspend () -> Response<ConfirmWatchingAdResponseModel> = {
                apiFactory.getApisHelper()
                    .confirmWatchingRewardAd(getLoginResponse().user?.id ?: "").await()
            }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override fun handleErrorResponseType(response: Response<ConfirmWatchingAdResponseModel>) {
                exceptionMessage.value = response.message()
            }
        }.asLiveData()
    }
}