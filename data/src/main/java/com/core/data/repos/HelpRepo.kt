package com.core.data.repos

import androidx.lifecycle.LiveData
import com.core.data.MainExceptions
import com.core.data.base.BaseRepo
import com.core.data.constant.SharedPrefKeys
import com.core.data.model.help.HelpResponseModel
import com.core.data.network.ApiFactory
import com.core.data.network.NetworkBoundFileResource
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference
import com.core.utils.FileManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

class HelpRepo(
    val apiFactory: ApiFactory,
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface,
    val fileManager: FileManager
) : BaseRepo(sharedPreferences, networkFactory) {
    fun requestHelpsList(): LiveData<HelpResponseModel> {
        return object : NetworkBoundFileResource<HelpResponseModel>(
            networkFactory,
            fileName = "helps_response",
            fileManager = fileManager
        ) {
            override fun convert(json: String): HelpResponseModel? {
                return Gson().fromJson(json, object : TypeToken<HelpResponseModel>() {}.type)
            }

            override suspend fun createCall(): suspend () -> Response<HelpResponseModel> = {
                apiFactory.getApisHelper().getHelpsList(getLanguage()).await()
            }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override fun handleErrorResponseType(response: Response<HelpResponseModel>) {
                exceptionMessage.value = response.message()
            }
        }.asLiveData()
    }

    fun getLanguage(): String = sharedPreference.getString(SharedPrefKeys.LANGUAGE)
}