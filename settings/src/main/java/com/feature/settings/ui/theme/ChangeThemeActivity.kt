package com.feature.settings.ui.theme

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.core.base.BaseActivity
import com.core.utils.AppConstant.DAY
import com.core.utils.AppConstant.NIGHT
import com.feature.settings.BR
import com.feature.settings.R
import com.feature.settings.databinding.ActivityChangeThemeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangeThemeActivity : BaseActivity<ActivityChangeThemeBinding, ChangeThemeViewModel>() {
    val changeThemeViewModel: ChangeThemeViewModel by viewModel()
    lateinit var themeType: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupEnvironment()
        nightContainerListener()
        dayContainerListener()
        saveButtonListener()
    }

    private fun setupEnvironment() {
        viewDataBinding.run {
            if (changeThemeViewModel.theme == DAY) {
                dayContainer.setBackgroundResource(R.color.dusk)
            } else {
                nightContainer.setBackgroundResource(R.color.dusk)
            }
            themeType = changeThemeViewModel.theme ?: ""
        }
    }

    private fun saveButtonListener() {
        viewDataBinding.save.setOnClickListener {
            changeThemeViewModel.saveTheme(themeType)
            val intent = Intent(this, Class.forName("com.app.etwak3.ui.MainActivity"))
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }

    private fun changeTheme() {
        if (themeType == NIGHT) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        recreate()
    }

    private fun nightContainerListener() {
        viewDataBinding.run {
            nightContainer.setOnClickListener {
                if (themeType != NIGHT) {
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
                if (themeType != DAY) {
                    dayContainer.setBackgroundColor(resources.getColor(R.color.shapes))
                    nightContainer.background = null
                    themeType = DAY
                }
            }
        }
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.activity_change_theme

    override fun controllerId(): Int = 0

    override fun getViewModel(): ChangeThemeViewModel = changeThemeViewModel
}
