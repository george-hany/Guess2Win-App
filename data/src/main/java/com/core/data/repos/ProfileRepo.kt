package com.core.data.repos

import androidx.lifecycle.LiveData
import com.core.data.MainExceptions
import com.core.data.base.BaseRepo
import com.core.data.constant.SharedPrefKeys.Companion.LOGIN_DATA
import com.core.data.model.login.LoginResponse
import com.core.data.model.profile.ChangePhoneRequest
import com.core.data.network.ApiFactory
import com.core.data.network.NetworkBoundFileResource
import com.core.data.strategy.DataStrategy
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference
import com.core.utils.FileManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

class ProfileRepo(
    val apiFactory: ApiFactory,
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface,
    val fileManager: FileManager
) : BaseRepo(sharedPreferences, networkFactory) {

    fun changePhone(changePhoneRequest: ChangePhoneRequest): LiveData<LoginResponse> {
        return object : NetworkBoundFileResource<LoginResponse>(
            networkFactory,
            DataStrategy.Strategies.NETWORK_ONLY,
            fileManager = null
        ) {
            override fun convert(json: String): LoginResponse? {
                return Gson().fromJson(json, object : TypeToken<LoginResponse>() {}.type)
            }

            override suspend fun createCall(): suspend () -> Response<LoginResponse> = {
                apiFactory.getApisHelper().changePhone(changePhoneRequest).await()
            }

            override fun handleErrorResponseType(response: Response<LoginResponse>) {
                exceptionMessage.value = response.message()
            }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }
        }.asLiveData()
    }

    fun getLoginResponseFromSharedPref(): LoginResponse {
        return Gson().fromJson(
            sharedPreference.getString(LOGIN_DATA),
            object : TypeToken<LoginResponse>() {}.type
        )
    }

    fun saveLoginResponseInSharedPref(loginResponse: LoginResponse) {
        sharedPreference.saveString(LOGIN_DATA, Gson().toJson(loginResponse))
    }
}