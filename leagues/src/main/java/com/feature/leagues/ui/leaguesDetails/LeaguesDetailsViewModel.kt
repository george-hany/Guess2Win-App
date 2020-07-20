package com.feature.leagues.ui.leaguesDetails

import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.repos.LeaguesDetailsRepo
import com.feature.leagues.ui.leagues.model.LeaguesItemUIModel

class LeaguesDetailsViewModel(var leaguesDetailsRepo: LeaguesDetailsRepo) :
    BaseViewModel<LeaguesDetailsRepo>(leaguesDetailsRepo){
    val leagueItemUIModel = MutableLiveData<LeaguesItemUIModel>()
}