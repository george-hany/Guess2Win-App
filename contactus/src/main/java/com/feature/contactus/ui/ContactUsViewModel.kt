package com.feature.contactus.ui

import com.core.base.BaseViewModel
import com.core.data.repos.ContactUsRepo

class ContactUsViewModel(var contactUsRepo: ContactUsRepo) :
    BaseViewModel<ContactUsRepo>(contactUsRepo)