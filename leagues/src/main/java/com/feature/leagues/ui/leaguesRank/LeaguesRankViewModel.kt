package com.feature.leagues.ui.leaguesRank

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.repos.LeaguesRankRepo
import com.feature.leagues.ui.leaguesRank.model.RankItemUIModel

class LeaguesRankViewModel(var leaguesRankRepo: LeaguesRankRepo) :
    BaseViewModel<LeaguesRankRepo>(leaguesRankRepo){
    val leaguesRankMediatorLiveData = MediatorLiveData<Any>()
    val ranksUIListLiveData = MutableLiveData<List<RankItemUIModel>>()

    fun getRanks(leagueId:String){
        val requestRanks = leaguesRankRepo.requestLeaguesRankList(leagueId)
        leaguesRankMediatorLiveData.addSource(requestRanks){response->
            ranksUIListLiveData.value = response.ranks?.map { RankItemUIModel.mapResponseToUI(it!!) }
        }
    }
}