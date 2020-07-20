package com.feature.matches.ui.matchDetails

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.model.matchDetails.MatchExpectationRequest
import com.core.data.model.matchDetails.MatchExpectationResponse
import com.core.data.repos.MatchDetailsRepo

class MatchDetailsViewModel(var matchDetailsRepo: MatchDetailsRepo) :
    BaseViewModel<MatchDetailsRepo>(matchDetailsRepo) {
    val matchDetailsMediatorLiveData = MediatorLiveData<Any>()
    val matchDetailsUIModel = MutableLiveData<MatchDetailsUIModel>()
    val matchExpectationRequest = MatchExpectationRequest()
    val matchExpectationResponse = MutableLiveData<MatchExpectationResponse>()
    fun getMatchDetails(matchId: String) {
        val requestMatchDetails = matchDetailsRepo.requestMatchDetails(matchId)
        matchDetailsMediatorLiveData.addSource(requestMatchDetails) {
            matchDetailsUIModel.value = MatchDetailsUIModel.mapResponseToUI(it)
        }
    }

    fun sendMatchExpectation() {
        val sendMatchExpectation = matchDetailsRepo.requestMatchExpectation(matchExpectationRequest)
        matchDetailsMediatorLiveData.addSource(sendMatchExpectation) {
            matchExpectationResponse.value = it
        }
    }
}