package com.feature.prizes.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.repos.PrizesRepo
import com.feature.prizes.ui.model.PrizeUIModel

class PrizesViewModel(var prizesRepo: PrizesRepo) : BaseViewModel<PrizesRepo>(prizesRepo) {

    val prizesMediatorLiveData = MediatorLiveData<Any>()
    val prizesUIListLiveData = MutableLiveData<List<PrizeUIModel>>()

    init {
        getPrizes()
    }

    fun getPrizes() {
        val requestPrizes = prizesRepo.getPrizes()
        prizesMediatorLiveData.addSource(requestPrizes) { response ->
            prizesUIListLiveData.value = response.data?.map { PrizeUIModel.mapResponseToUI(it!!) }
        }
    }
}