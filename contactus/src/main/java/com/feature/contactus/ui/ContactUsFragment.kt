package com.feature.contactus.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.core.base.BaseFragment
import com.core.utils.CommonUtils.getFacebookPageURL
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        visitFacebookPageListener()
    }

    private fun visitFacebookPageListener() {
        viewDataBinding.visitFacebookPage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(getFacebookPageURL(requireContext()))
            startActivity(intent)
        }
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_contact_us

    override fun getViewModel(): ContactUsViewModel = contactUsViewModel

    override fun handleError() {}
}
