package com.feature.settings.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.core.base.BaseFragment
import com.core.utils.AppConstant.DAY
import com.core.utils.AppConstant.NIGHT
import com.feature.settings.BR
import com.feature.settings.R
import com.feature.settings.databinding.FragmentSettingsBinding
import com.feature.settings.ui.theme.ChangeThemeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment() : BaseFragment<FragmentSettingsBinding, SettingsViewModel>() {
    var isThemeSwichChecked = false
    var isNotificationSwitchChecked = false
    val settingsViewModel: SettingsViewModel by viewModel()
    var callBack: CallBack? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSubFragment = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notificationStatusObserver()
        themeStatusObserver()
        setupLanguageSwitch()
        themeSwitchListener()
        notificationListener()
        languageListener()
        viewDataBinding.text2.setOnClickListener {
            startActivity(
                Intent(
                    context,
                    ChangeThemeActivity::class.java
                )
            )
        }
    }

    private fun languageListener() {
        viewDataBinding.run {
            languageSwitch.setOnCheckedChangeListener { _, isChecked ->
                val currentLocal = baseActivity?.resources?.configuration?.locale
                if (isChecked) {
                    if ((currentLocal?.toLanguageTag() ?: "") == "ar") {
                        baseActivity?.setNewLocale("EN")
                    } else if ((currentLocal?.toLanguageTag() ?: "") == "en") {
                        baseActivity?.setNewLocale("AR")
                    }
                    viewDataBinding.languageSwitch.isChecked = false
                    requireActivity().supportFragmentManager.beginTransaction()
                        .remove(this@SettingsFragment).commit()
                }
            }
        }
    }

    private fun notificationListener() {
        viewDataBinding.run {
            notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
                settingsViewModel.saveNotificationStatus(!isChecked)
            }
        }
    }

    private fun themeSwitchListener() {
        viewDataBinding.run {
            themeSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    if (settingsViewModel.themeStatus != DAY) {
                        callBack?.onChangeTheme(DAY)
                    } else if (settingsViewModel.themeStatus != NIGHT) {
                        callBack?.onChangeTheme(NIGHT)
                    }
                    viewDataBinding.languageSwitch.isChecked = false
                }
            }
        }
    }

    private fun setupLanguageSwitch() {
        val currentLocal = baseActivity?.resources?.configuration?.locale
        if ((currentLocal?.toLanguageTag() ?: "") == "ar") {
            viewDataBinding.languageText.text = "English"
        } else if ((currentLocal?.toLanguageTag() ?: "") == "en") {
            viewDataBinding.languageText.text = "العربية"
        }
    }

    private fun themeStatusObserver() {
        settingsViewModel.themeStatus?.let {
            if (it == DAY) {
                viewDataBinding.themeText.text = getString(R.string.night_mood)
            } else {
                viewDataBinding.themeText.text = getString(R.string.day_mood)
            }
        }
    }

    private fun notificationStatusObserver() {
        settingsViewModel.notificationStatus.let { isDeactivate ->
            viewDataBinding.notificationSwitch.isChecked = !isDeactivate
            isNotificationSwitchChecked = !isDeactivate
        }
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_settings

    override fun getViewModel(): SettingsViewModel = settingsViewModel

    companion object {
        fun newInstance(callBack: CallBack) = SettingsFragment(callBack)
    }

    constructor(callBack: CallBack) : this() {
        this.callBack = callBack
    }

    interface CallBack {
        fun onChangeTheme(themeType: String)
    }
}
