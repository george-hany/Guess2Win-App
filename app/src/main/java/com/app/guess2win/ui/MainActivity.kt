package com.app.guess2win.ui

import android.content.Intent
import com.app.guess2win.BR
import com.app.guess2win.R
import com.app.guess2win.databinding.ActivityMainBinding
import com.core.base.BaseActivity
import com.core.utils.AppConstant.loginRequest
import com.core.utils.AppConstant.loginSuccess
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    val mainViewModel: MainViewModel by viewModel()

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
}
