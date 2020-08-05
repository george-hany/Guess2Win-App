package com.feature.choose_theme.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.core.base.BaseFragment
import com.core.utils.AppConstant
import com.core.utils.AppConstant.DAY
import com.core.utils.AppConstant.NIGHT
import com.feature.choose_theme.BR
import com.feature.choose_theme.R
import com.feature.choose_theme.databinding.FragmentChooseThemeBinding
import com.feature.login.ui.LoginFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ChooseThemeFragment : BaseFragment<FragmentChooseThemeBinding, ChooseThemeViewModel>() {
    var themeType = DAY
    val chooseThemeViewModel: ChooseThemeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dayContainerListener()
        nightContainerListener()
        saveButtonListener()
    }

    private fun saveButtonListener() {
        viewDataBinding.save.setOnClickListener {
            chooseThemeViewModel.saveThemeType(themeType)
            changeTheme()
            activity?.startActivityForResult(
                Intent(context, LoginFragment::class.java),
                AppConstant.loginRequest
            )
        }
    }

    private fun changeTheme() {
        if (themeType == NIGHT) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun nightContainerListener() {
        viewDataBinding.run {
            nightContainer.setOnClickListener {
                if (themeType == DAY) {
                    nightContainer.setBackgroundColor(resources.getColor(R.color.shapes))
                    dayContainer.background = null
                    themeType = NIGHT
                }
            }
        }
    }

    private fun dayContainerListener() {
        viewDataBinding.run {
            dayContainer.setOnClickListener {
                if (themeType == NIGHT) {
                    dayContainer.setBackgroundColor(resources.getColor(R.color.shapes))
                    nightContainer.background = null
                    themeType = DAY
                }
            }
        }
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_choose_theme

    override fun getViewModel(): ChooseThemeViewModel = chooseThemeViewModel

    override fun handleError() {}
}
