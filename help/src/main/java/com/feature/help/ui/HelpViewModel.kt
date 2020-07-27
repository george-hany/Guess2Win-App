package com.feature.help.ui

import com.core.base.BaseViewModel
import com.core.data.repos.HelpRepo

class HelpViewModel(var helpRepo: HelpRepo) : BaseViewModel<HelpRepo>(helpRepo)