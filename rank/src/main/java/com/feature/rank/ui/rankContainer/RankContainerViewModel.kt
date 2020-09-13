package com.feature.rank.ui.rankContainer

import com.core.base.BaseViewModel
import com.core.data.repos.RankContainerRepo

class RankContainerViewModel(var rankContainerRepo: RankContainerRepo) :
    BaseViewModel<RankContainerRepo>(rankContainerRepo)