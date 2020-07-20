package com.feature.contactus.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.core.base.BaseFragment
import com.feature.contactus.BR
import com.feature.contactus.R
import com.feature.contactus.databinding.FragmentContactUsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ContactUsFragment : BaseFragment<FragmentContactUsBinding, ContactUsViewModel>() {
    val contactUsViewModel: ContactUsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSubFragment = true
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_contact_us

    override fun getViewModel(): ContactUsViewModel = contactUsViewModel
}
