package com.feature.help.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.core.base.BaseFragment
import com.feature.help.BR
import com.feature.help.R
import com.feature.help.databinding.FragmentHelpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class HelpFragment : BaseFragment<FragmentHelpBinding, HelpViewModel>() {
    val helpViewModel: HelpViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSubFragment = true
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_help

    override fun getViewModel(): HelpViewModel = helpViewModel
}
