package com.feature.extrapoints.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.repos.ExtraPointsRepo

class ExtraPointsViewModel(var extraPointsRepo: ExtraPointsRepo) :
    BaseViewModel<ExtraPointsRepo>(extraPointsRepo) {
    val extraPointsMediatorLiveData = MediatorLiveData<Any>()
    val checkRewardAdAvailabilityLiveData = MutableLiveData<Boolean>()
    val confirmWatchingRewardAd = MutableLiveData<Boolean>()

    fun checkRewardAdAvailability() {
        setIsLoading(true)
        val requestCheckRewardAd = extraPointsRepo.requestRewardAdAvailability()
        extraPointsMediatorLiveData.addSource(requestCheckRewardAd) {
            checkRewardAdAvailabilityLiveData.value = it
            setIsLoading(false)
        }
    }

    fun confirmWatchingAd(){
        setIsLoading(true)
        val requestConfirmWatchingAd = extraPointsRepo.confirmWatchingAd()
        extraPointsMediatorLiveData.addSource(requestConfirmWatchingAd){
            confirmWatchingRewardAd.value = true
            setIsLoading(false)
        }
    }
}