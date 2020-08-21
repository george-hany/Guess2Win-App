package com.feature.matches.ui.matches

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.repos.MatchesRepo
import com.core.utils.CommonUtils.getCurrentDate
import com.core.utils.CommonUtils.getDateBeforeOrAfter
import com.feature.matches.ui.matches.model.MatchItemUIModel

class MatchesViewModel(var matchesRepo: MatchesRepo) : BaseViewModel<MatchesRepo>(matchesRepo) {
    val selectedDate = MutableLiveData<String>()
    val matchesMediatorLiveData = MediatorLiveData<Any>()
    val matchesListLiveData = MutableLiveData<List<MatchItemUIModel>>()

    init {
        selectedDate.value = getCurrentDate()
    }

    fun getMatchesByDate(date: String) {
        val requestMatches = matchesRepo.requestMatchesByDate(date)
        matchesMediatorLiveData.addSource(requestMatches) { response ->
            matchesListLiveData.value =
                response.data?.map { MatchItemUIModel.mapResponseToUI(it!!) }
            matchesMediatorLiveData.removeSource(requestMatches)
        }
    }

    fun getLeaguesMatchesByDate(date: String, leaguesId: String) {
        val requestMatches = matchesRepo.requestLeaguesMatchesByDate(date, leaguesId)
        matchesMediatorLiveData.addSource(requestMatches) { response ->
            matchesListLiveData.value =
                response.data?.map { MatchItemUIModel.mapResponseToUI(it!!) }
        }
    }

    fun onPreviousClick() {
        selectedDate.run {
            value = getDateBeforeOrAfter(value!!, -1)
        }
    }

    fun onNextClick() {
        selectedDate.run {
            value = getDateBeforeOrAfter(value!!, 1)
        }
    }
}