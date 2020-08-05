package com.feature.profile.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.model.profile.ChangePhoneRequest
import com.core.data.repos.ProfileRepo
import com.feature.profile.R

class ProfileViewModel(var profileRepo: ProfileRepo) : BaseViewModel<ProfileRepo>(profileRepo) {
    var isValidNumber: Boolean = false
    var changePhoneRequest = ChangePhoneRequest()
    var phoneNumberError = MutableLiveData<Int>()
    val profileMediatorLiveData = MediatorLiveData<Any>()
    val changePasswordSuccess = MutableLiveData<Boolean>()
    val loginResponse = MutableLiveData(profileRepo.getLoginResponseFromSharedPref())
    fun savePhone() {
        if (!validate())
            return
        val requestChangePhone = profileRepo.changePhone(changePhoneRequest)
        profileMediatorLiveData.addSource(requestChangePhone) {
            changePasswordSuccess.value = true
        }
    }

    private fun validate(): Boolean {
        if (isValidNumber)
            return true
        phoneNumberError.value = R.string.Invalid_Phone_Number
        return false
    }
}