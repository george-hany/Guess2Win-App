package com.app.app.ui

import com.core.base.BaseViewModel
import com.core.data.repos.MainRepo

class MainViewModel(val mainRepo: MainRepo) :
    BaseViewModel<MainRepo>(mainRepo)
