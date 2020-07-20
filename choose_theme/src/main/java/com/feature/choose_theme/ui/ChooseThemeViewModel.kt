package com.feature.choose_theme.ui

import com.core.base.BaseViewModel
import com.core.data.repos.ChooseThemeRepo

class ChooseThemeViewModel(var chooseThemeRepo: ChooseThemeRepo) :
    BaseViewModel<ChooseThemeRepo>(chooseThemeRepo) {

    fun saveThemeType(type: String) {
        chooseThemeRepo.saveThemeTypeToSharedPref(type)
    }
}