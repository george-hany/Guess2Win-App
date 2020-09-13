package com.feature.help.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.repos.HelpRepo
import com.feature.help.ui.model.HelpItemModel

class HelpViewModel(var helpRepo: HelpRepo) : BaseViewModel<HelpRepo>(helpRepo) {
    val helpsMediatorLiveData = MediatorLiveData<Any>()
    val helpsItemUIListLiveData = MutableLiveData<List<HelpItemModel>>()

    init {
        getHelpsList()
    }

    fun getHelpsList() {
        val requestHelpsList = helpRepo.requestHelpsList()
        helpsMediatorLiveData.addSource(requestHelpsList) { response ->
            helpsItemUIListLiveData.value =
                response.data?.map { HelpItemModel.mapResponseToUI(it!!) }
        }
    }
}