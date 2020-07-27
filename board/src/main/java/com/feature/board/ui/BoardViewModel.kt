package com.feature.board.ui

import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.repos.BoardRepo

class BoardViewModel(var boardRepo: BoardRepo) : BaseViewModel<BoardRepo>(boardRepo) {
    var sideBarClickPosition = MutableLiveData<Int>()
    fun sideBarClicks(position: Int) {
        sideBarClickPosition.value = position
    }

    fun setIsLoggedInToFalse() {
        boardRepo.setLoggedInToFalseInSharedPref()
    }

    fun saveTheme(themeType: String) {
        boardRepo.saveThemeTypeToSharedPref(themeType)
    }

    fun getTheme() = boardRepo.getAppThemeFromSharedPref()
}
