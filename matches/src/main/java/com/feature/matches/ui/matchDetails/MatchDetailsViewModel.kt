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
    val matchItemUIModel = MutableLiveData<MatchDetailsUIModel>()
    val matchExpectationRequest = MatchExpectationRequest()
    val matchExpectationResponse = MutableLiveData<MatchExpectationResponse>()
    fun getMatchDetails(matchId: String) {
        setIsLoading(true)
        val requestMatchDetails = matchDetailsRepo.requestMatchDetails(matchId)
        matchDetailsMediatorLiveData.addSource(requestMatchDetails) {
            matchItemUIModel.value = MatchDetailsUIModel.mapResponseToUI(it.data!!)
            setIsLoading(false)
        }
    }

    fun sendMatchExpectation() {
        setIsLoading(true)
        val sendMatchExpectation = matchDetailsRepo.requestMatchExpectation(matchExpectationRequest)
        matchDetailsMediatorLiveData.addSource(sendMatchExpectation) {
            matchExpectationResponse.value = it
            getMatchDetails(matchItemUIModel.value?.matchId ?: "")
            setIsLoading(false)
        }
    }
}