package com.feature.choose_language.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.core.base.BaseFragment
import com.core.utils.AppConstant.ARABIC_CODE
import com.core.utils.AppConstant.ENGLISH_CODE
import com.feature.choose_language.BR
import com.feature.choose_language.R
import com.feature.choose_language.databinding.FragmentChooseLanguageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ChooseLanguageFragment :
    BaseFragment<FragmentChooseLanguageBinding, ChooseLanguageViewModel>() {

    val chooseLanguageViewModel: ChooseLanguageViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveButtonListener()
    }

    private fun saveButtonListener() {
        viewDataBinding.save.setOnClickListener {
            saveLanguageToSharedPref()
            navigateToUri(R.string.chooseTheme)
        }
    }

    private fun saveLanguageToSharedPref() {
        val selected = viewDataBinding.languagesGroup.checkedRadioButtonId
        if (selected == R.id.english_RB) {
            chooseLanguageViewModel.saveLanguage(ENGLISH_CODE)
            baseActivity?.setNewLocale(ENGLISH_CODE)
        } else {
            chooseLanguageViewModel.saveLanguage(ARABIC_CODE)
            baseActivity?.setNewLocale(ARABIC_CODE)
        }
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_choose_language

    override fun getViewModel(): ChooseLanguageViewModel = chooseLanguageViewModel

    override fun handleError() {}
}
