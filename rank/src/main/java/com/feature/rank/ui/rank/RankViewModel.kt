package com.feature.rank.ui.rank

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.model.rank.MonthResponseModel
import com.core.data.repos.RankRepo
import com.feature.rank.ui.rank.model.RanksItemUIModel

class RankViewModel(var rankRepo: RankRepo) : BaseViewModel<RankRepo>(rankRepo) {
    val selectedDate = MutableLiveData<String?>(null)
    var selectedNum = 0
    var rankUIType = ""
    var rankNetworkType = ""
    val rankMediatorLiveData = MediatorLiveData<Any>()
    val ranksUIListLiveData = MutableLiveData<List<RanksItemUIModel>>()
    val userId = rankRepo.getLoginResponseFromSharedPref().user?.id
    val namesListLiveData = MutableLiveData<MonthResponseModel>()
    val userRankItem = MutableLiveData<RanksItemUIModel>(null)
    val userIndex = MutableLiveData<String>(null)

    fun getMonths() {
        val requestMonths = rankRepo.requestMonths()
        rankMediatorLiveData.addSource(requestMonths) { list ->
            namesListLiveData.value = list
        }
    }

    fun getRanksByMonth() {
        val requestRanks =
            rankRepo.requestRanksByMonth(namesListLiveData.value?.data?.get(selectedNum)?.id ?: 0)
        rankMediatorLiveData.addSource(requestRanks) { response ->
            ranksUIListLiveData.value =
                response?.map { RanksItemUIModel.mapRanksByMonthToUI(it) }
            userRankItem.value = ranksUIListLiveData.value?.firstOrNull { it.id == userId }
            userIndex.value =
                (ranksUIListLiveData.value?.indexOf(userRankItem.value)?.plus(1) ?: -1).toString()
        }
    }

    fun getWeeks() {
        val requestMonths = rankRepo.requestWeeks()
        rankMediatorLiveData.addSource(requestMonths) { list ->
            namesListLiveData.value = list
        }
    }

    fun getRanksByWeek() {
        val requestRanks =
            rankRepo.requestRanksByWeek(namesListLiveData.value?.data?.get(selectedNum)?.id ?: 0)
        rankMediatorLiveData.addSource(requestRanks) { response ->
            ranksUIListLiveData.value =
                response?.map { RanksItemUIModel.mapRanksByMonthToUI(it) }
            userRankItem.value = ranksUIListLiveData.value?.firstOrNull { it.id == userId }
            userIndex.value =
                (ranksUIListLiveData.value?.indexOf(userRankItem.value)?.plus(1) ?: -1).toString()
        }
    }

    fun getSeasons() {
        val requestMonths = rankRepo.requestSeasons()
        rankMediatorLiveData.addSource(requestMonths) { list ->
            namesListLiveData.value = list
        }
    }

    fun getRanksBySeason() {
        val requestRanks =
            rankRepo.requestRanksBySeason(namesListLiveData.value?.data?.get(selectedNum)?.id ?: 0)
        rankMediatorLiveData.addSource(requestRanks) { response ->
            ranksUIListLiveData.value =
                response?.map { RanksItemUIModel.mapRanksByMonthToUI(it) }
            userRankItem.value = ranksUIListLiveData.value?.firstOrNull { it.id == userId }
            userIndex.value =
                (ranksUIListLiveData.value?.indexOf(userRankItem.value)?.plus(1) ?: -1).toString()
        }
    }

    fun onPreviousClick() {
        if (selectedNum > 0) {
            selectedDate.value = namesListLiveData.value?.data?.get(--selectedNum)?.name
        }
    }

    fun onNextClick() {
        if (selectedNum < namesListLiveData.value?.data?.size!! - 1)
            selectedDate.value = namesListLiveData.value?.data?.get(++selectedNum)?.name
    }
}