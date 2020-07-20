package com.feature.profile.ui

import com.core.base.BaseViewModel
import com.core.data.repos.ProfileRepo

class ProfileViewModel(var profileRepo: ProfileRepo) : BaseViewModel<ProfileRepo>(profileRepo)