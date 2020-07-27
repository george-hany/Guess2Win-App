package com.feature.leagues.ui.leagues

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.repos.LeaguesRepo
import com.feature.leagues.ui.leagues.model.LeaguesItemUIModel

class LeaguesViewModel(var leaguesRepo: LeaguesRepo) : BaseViewModel<LeaguesRepo>(leaguesRepo) {
    val leaguesMediatorLiveData = MediatorLiveData<Any>()
    val leaguesUIListLiveData = MutableLiveData<List<LeaguesItemUIModel>>()

    init {
        getLeaguesList()
    }

    private fun getLeaguesList() {
        val requestLeagues = leaguesRepo.requestLeaguesList()
        leaguesMediatorLiveData.addSource(requestLeagues) { response ->
            leaguesUIListLiveData.value =
                response.result?.map { LeaguesItemUIModel.mapResponseToUI(it!!) }
        }
    }
}