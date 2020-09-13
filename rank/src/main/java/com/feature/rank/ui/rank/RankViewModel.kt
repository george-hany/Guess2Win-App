package com.feature.rank.ui.rank

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
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
    val idsListLiveData = MutableLiveData<List<Int>>()

    fun getMonths() {
        val requestMonths = rankRepo.requestMonths()
        rankMediatorLiveData.addSource(requestMonths) { list ->
            idsListLiveData.value = list.map { it.id }
        }
    }

    fun getRanksByMonth() {
        val requestRanks =
            rankRepo.requestRanksByMonth(idsListLiveData.value?.get(selectedNum) ?: 0)
        rankMediatorLiveData.addSource(requestRanks) { response ->
            ranksUIListLiveData.value =
                response?.map { RanksItemUIModel.mapRanksByMonthToUI(it) }
        }
    }

    fun getWeeks() {
        val requestMonths = rankRepo.requestWeeks()
        rankMediatorLiveData.addSource(requestMonths) { list ->
            idsListLiveData.value = list.map { it.id }
        }
    }

    fun getRanksByWeek() {
        val requestRanks =
            rankRepo.requestRanksByWeek(idsListLiveData.value?.get(selectedNum) ?: 0)
        rankMediatorLiveData.addSource(requestRanks) { response ->
            ranksUIListLiveData.value =
                response?.map { RanksItemUIModel.mapRanksByMonthToUI(it) }
        }
    }

    fun getSeasons() {
        val requestMonths = rankRepo.requestSeasons()
        rankMediatorLiveData.addSource(requestMonths) { list ->
            idsListLiveData.value = list.map { it.id }
        }
    }

    fun getRanksBySeason() {
        val requestRanks =
            rankRepo.requestRanksBySeason(idsListLiveData.value?.get(selectedNum) ?: 0)
        rankMediatorLiveData.addSource(requestRanks) { response ->
            ranksUIListLiveData.value =
                response?.map { RanksItemUIModel.mapRanksByMonthToUI(it) }
        }
    }

    fun onPreviousClick() {
        if (selectedNum > 0) {
            selectedDate.value = "$rankUIType ${idsListLiveData.value?.get(--selectedNum)}"
        }
    }

    fun onNextClick() {
        if (selectedNum < idsListLiveData.value?.size!! - 1)
            selectedDate.value = "$rankUIType ${idsListLiveData.value?.get(++selectedNum)}"
    }
}