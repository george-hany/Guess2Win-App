package com.feature.rank.ui.rank

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.repos.RankRepo
import com.feature.rank.ui.rank.model.RanksItemUIModel

class RankViewModel(var rankRepo: RankRepo) : BaseViewModel<RankRepo>(rankRepo) {
    val selectedDate = MutableLiveData<String>()
    var selectedNum = 1
    var rankUIType = ""
    var rankNetworkType = ""
    val rankMediatorLiveData = MediatorLiveData<Any>()
    val ranksUIListLiveData = MutableLiveData<List<RanksItemUIModel>>()
    val userId = rankRepo.getLoginResponseFromSharedPref().user?.id

    fun getRanks() {
        val requestRanks = rankRepo.requestRanksList(rankNetworkType, selectedNum)
        rankMediatorLiveData.addSource(requestRanks) { response ->
            ranksUIListLiveData.value =
                response.ranks?.map { RanksItemUIModel.mapResponseToUI(it!!) }
        }
    }

    fun getRanksByMonth() {
        val requestRanks = rankRepo.requestRanksByMonth(selectedNum)
        rankMediatorLiveData.addSource(requestRanks) { response ->
            ranksUIListLiveData.value =
                response?.map { RanksItemUIModel.mapRanksByMonthToUI(it) }
        }
    }

    fun onPreviousClick() {
        if (selectedNum > 1) {
            selectedDate.value = "$rankUIType ${--selectedNum}"
        }
    }

    fun onNextClick() {
        selectedDate.value = "$rankUIType ${++selectedNum}"
    }
}