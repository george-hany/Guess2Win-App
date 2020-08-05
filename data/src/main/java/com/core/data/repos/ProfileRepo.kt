package com.core.data.repos

import androidx.lifecycle.LiveData
import com.core.data.MainExceptions
import com.core.data.base.BaseRepo
import com.core.data.constant.SharedPrefKeys.Companion.LOGIN_DATA
import com.core.data.model.login.LoginResponse
import com.core.data.model.profile.ChangePhoneRequest
import com.core.data.model.profile.ChangePhoneResponse
import com.core.data.network.ApiFactory
import com.core.data.network.NetworkBoundFileResource
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

    fun changePhone(changePhoneRequest: ChangePhoneRequest): LiveData<ChangePhoneResponse> {
        networkFactory.setFileName("change_phone_response.json")
        return object : NetworkBoundFileResource<ChangePhoneResponse>(
            networkFactory,
            fileManager = null
        ) {
            override fun convert(json: String): ChangePhoneResponse? {
                return Gson().fromJson(json, object : TypeToken<ChangePhoneResponse>() {}.type)
            }

            override suspend fun createCall(): suspend () -> Response<ChangePhoneResponse> = {
                apiFactory.getApisHelper().changePhone(changePhoneRequest).await()
            }

            override fun handleErrorResponseType(response: Response<ChangePhoneResponse>) {}

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
}