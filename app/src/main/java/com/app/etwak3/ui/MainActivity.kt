package com.app.etwak3.ui

import android.content.Intent
import com.app.etwak3.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.app.etwak3.R
import com.core.base.BaseActivity
import com.app.etwak3.BR
import com.core.utils.AppConstant.loginRequest
import com.core.utils.AppConstant.loginSuccess

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
