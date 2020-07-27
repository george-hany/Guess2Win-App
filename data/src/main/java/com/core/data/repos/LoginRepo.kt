package com.core.data.repos

import androidx.lifecycle.LiveData
import com.core.data.MainExceptions
import com.core.data.base.BaseRepo
import com.core.data.constant.SharedPrefKeys.Companion.IS_LOGGED_IN
import com.core.data.constant.SharedPrefKeys.Companion.LOGIN_DATA
import com.core.data.network.ApiFactory
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference
import com.google.gson.Gson
import com.core.data.model.login.LoginRequest
import com.core.data.model.login.LoginResponse
import com.core.data.network.NetworkBoundFileResource
import com.core.data.strategy.DataStrategy
import com.google.gson.reflect.TypeToken
import retrofit2.Response

open class LoginRepo(
    val apiFactory: ApiFactory,
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface
) : BaseRepo(sharedPreferences, networkFactory) {

    fun saveLoginResponseInSharedPref(loginResponse: LoginResponse) {
        sharedPreference.saveString(LOGIN_DATA, Gson().toJson(loginResponse))
        sharedPreference.saveBoolean(IS_LOGGED_IN, true)
    }

    fun login(loginRequest: LoginRequest): LiveData<LoginResponse> {
        networkFactory.setFileName("login_response.json")
        return object : NetworkBoundFileResource<LoginResponse>(
            networkFactory,
            DataStrategy.Strategies.NETWORK_ONLY,
            fileManager = null
        ) {
            override fun convert(json: String): LoginResponse? {
                return Gson().fromJson(json, object : TypeToken<LoginResponse>() {}.type)
            }

            override suspend fun createCall(): suspend () -> Response<LoginResponse> = {
                apiFactory.getApisHelper().login(loginRequest).await()
            }

            override fun handleErrorResponseType(response: Response<LoginResponse>) {}

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }
        }.asLiveData()
    }
}