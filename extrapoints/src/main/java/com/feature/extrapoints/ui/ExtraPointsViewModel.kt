package com.feature.extrapoints.ui

import com.core.base.BaseViewModel
import com.core.data.repos.ExtraPointsRepo

class ExtraPointsViewModel(var extraPointsRepo: ExtraPointsRepo) :
    BaseViewModel<ExtraPointsRepo>(extraPointsRepo)