package com.feature.choose_language.ui

import com.core.base.BaseViewModel
import com.core.data.repos.ChooseLanguageRepo

class ChooseLanguageViewModel(var chooseLanguageRepo: ChooseLanguageRepo) :
    BaseViewModel<ChooseLanguageRepo>(chooseLanguageRepo) {

    fun saveLanguage(language: String) {
        chooseLanguageRepo.saveLanguageToSharedPref(language)
    }
}