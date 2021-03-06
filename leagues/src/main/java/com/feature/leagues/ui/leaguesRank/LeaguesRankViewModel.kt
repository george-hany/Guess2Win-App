package com.feature.leagues.ui.leaguesRank

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.repos.LeaguesRankRepo
import com.feature.leagues.ui.leaguesRank.model.RankItemUIModel

class LeaguesRankViewModel(var leaguesRankRepo: LeaguesRankRepo) :
    BaseViewModel<LeaguesRankRepo>(leaguesRankRepo) {
    val leaguesRankMediatorLiveData = MediatorLiveData<Any>()
    val ranksUIListLiveData = MutableLiveData<List<RankItemUIModel>>()
    val userId = leaguesRankRepo.getLoginResponseFromSharedPref().user?.id
    val userRankItem = MutableLiveData<RankItemUIModel>(null)
    val userIndex = MutableLiveData<String>(null)

    fun getRanks(leagueId: String) {
        val requestRanks = leaguesRankRepo.requestLeaguesRankList(leagueId)
        leaguesRankMediatorLiveData.addSource(requestRanks) { response ->
            ranksUIListLiveData.value =
                response?.map { RankItemUIModel.mapResponseToUI(it) }
            userRankItem.value = ranksUIListLiveData.value?.firstOrNull { it.id == userId }
            userIndex.value =
                (ranksUIListLiveData.value?.indexOf(userRankItem.value)?.plus(1) ?: -1).toString()
        }
    }
}