package com.core.data.repos

import androidx.lifecycle.LiveData
import com.core.data.MainExceptions
import com.core.data.base.BaseRepo
import com.core.data.constant.SharedPrefKeys
import com.core.data.model.login.LoginResponse
import com.core.data.model.rank.MonthResponseModel
import com.core.data.model.rank.RankByMonthResponseModel
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

    fun getLoginResponseFromSharedPref(): LoginResponse = Gson().fromJson(
        sharedPreference.getString(SharedPrefKeys.LOGIN_DATA),
        object : TypeToken<LoginResponse>() {}.type
    )

    fun getLanguageFromSharedPref() = sharedPreference.getString(SharedPrefKeys.LANGUAGE)

    fun requestRanksByMonth(selectedNum: Int): LiveData<List<RankByMonthResponseModel>> {
//        this.networkFactory.setFileName("ranks_by_month_$selectedNum.json")
        return object : NetworkBoundFileResource<List<RankByMonthResponseModel>>(
            networkFactory,
            fileName = "ranks_by_month_$selectedNum.json",
            fileManager = fileManager
        ) {
            override fun convert(json: String): List<RankByMonthResponseModel>? {
                return Gson().fromJson(
                    json,
                    object : TypeToken<List<RankByMonthResponseModel>>() {}.type
                )
            }

            override suspend fun createCall(): suspend () -> Response<List<RankByMonthResponseModel>> = {
                    apiFactory.getApisHelper().getRanksByMonth(selectedNum).await()
                }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override fun handleErrorResponseType(response: Response<List<RankByMonthResponseModel>>) {}
        }.asLiveData()
    }

    fun requestMonths(): LiveData<MonthResponseModel> {
        return object : NetworkBoundFileResource<MonthResponseModel>(
            networkFactory,
            fileName = "months.json",
            fileManager = fileManager
        ) {
            override fun convert(json: String): MonthResponseModel? {
                return Gson().fromJson(
                    json,
                    object : TypeToken<MonthResponseModel>() {}.type
                )
            }

            override suspend fun createCall(): suspend () -> Response<MonthResponseModel> = {
                    apiFactory.getApisHelper().getMonths(getLanguageFromSharedPref()).await()
                }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override fun handleErrorResponseType(response: Response<MonthResponseModel>) {}
        }.asLiveData()
    }

    fun requestWeeks(): LiveData<MonthResponseModel> {
        return object : NetworkBoundFileResource<MonthResponseModel>(
            networkFactory,
            fileName = "weeks.json",
            fileManager = fileManager
        ) {
            override fun convert(json: String): MonthResponseModel? {
                return Gson().fromJson(
                    json,
                    object : TypeToken<MonthResponseModel>() {}.type
                )
            }

            override suspend fun createCall(): suspend () -> Response<MonthResponseModel> = {
                    apiFactory.getApisHelper().getWeeks(getLanguageFromSharedPref()).await()
                }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override fun handleErrorResponseType(response: Response<MonthResponseModel>) {}
        }.asLiveData()
    }

    fun requestRanksByWeek(selectedNum: Int): LiveData<List<RankByMonthResponseModel>> {
//        this.networkFactory.setFileName("ranks_by_month_$selectedNum.json")
        return object : NetworkBoundFileResource<List<RankByMonthResponseModel>>(
            networkFactory,
            fileName = "ranks_by_month_$selectedNum.json",
            fileManager = fileManager
        ) {
            override fun convert(json: String): List<RankByMonthResponseModel>? {
                return Gson().fromJson(
                    json,
                    object : TypeToken<List<RankByMonthResponseModel>>() {}.type
                )
            }

            override suspend fun createCall(): suspend () -> Response<List<RankByMonthResponseModel>> = {
                    apiFactory.getApisHelper().getRanksByWeek(selectedNum).await()
                }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override fun handleErrorResponseType(response: Response<List<RankByMonthResponseModel>>) {}
        }.asLiveData()
    }

    fun requestSeasons(): LiveData<MonthResponseModel> {
        return object : NetworkBoundFileResource<MonthResponseModel>(
            networkFactory,
            fileName = "months.json",
            fileManager = fileManager
        ) {
            override fun convert(json: String): MonthResponseModel? {
                return Gson().fromJson(
                    json,
                    object : TypeToken<MonthResponseModel>() {}.type
                )
            }

            override suspend fun createCall(): suspend () -> Response<MonthResponseModel> = {
                    apiFactory.getApisHelper().getSeasons(getLanguageFromSharedPref()).await()
                }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override fun handleErrorResponseType(response: Response<MonthResponseModel>) {}
        }.asLiveData()
    }

    fun requestRanksBySeason(selectedNum: Int): LiveData<List<RankByMonthResponseModel>> {
//        this.networkFactory.setFileName("ranks_by_month_$selectedNum.json")
        return object : NetworkBoundFileResource<List<RankByMonthResponseModel>>(
            networkFactory,
            fileName = "ranks_by_month_$selectedNum.json",
            fileManager = fileManager
        ) {
            override fun convert(json: String): List<RankByMonthResponseModel>? {
                return Gson().fromJson(
                    json,
                    object : TypeToken<List<RankByMonthResponseModel>>() {}.type
                )
            }

            override suspend fun createCall(): suspend () -> Response<List<RankByMonthResponseModel>> = {
                    apiFactory.getApisHelper().getRanksBySeason(selectedNum).await()
                }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override fun handleErrorResponseType(response: Response<List<RankByMonthResponseModel>>) {}
        }.asLiveData()
    }
}