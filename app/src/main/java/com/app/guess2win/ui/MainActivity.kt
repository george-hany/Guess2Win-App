package com.app.guess2win.ui

import android.content.Intent
import android.os.Bundle
import com.app.guess2win.BR
import com.app.guess2win.R
import com.app.guess2win.databinding.ActivityMainBinding
import com.core.base.BaseActivity
import com.core.utils.AppConstant.loginRequest
import com.core.utils.AppConstant.loginSuccess
import com.mopub.common.MoPub
import com.mopub.common.SdkConfiguration
import com.mopub.common.SdkInitializationListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MoPub.initializeSdk(this, getSdkConfiguration(), initSdkListener())
    }

    override fun bindingVariable(): Int {
        return BR.viewModel
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun controllerId(): Int {
        return R.id.mainController
    }

    override fun getViewModel(): MainViewModel {
        return mainViewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == loginRequest && resultCode == loginSuccess) {
            navigateToUriWithClearStack(R.string.board)
        }
    }

    private fun initSdkListener(): SdkInitializationListener? {
        return SdkInitializationListener { /* MoPub SDK initialized.
           Check if you should show the consent dialog here, and make your ad requests. */
        }
    }

    fun getSdkConfiguration() = SdkConfiguration.Builder(getString(R.string.Banner_ID))
        .build()
}
