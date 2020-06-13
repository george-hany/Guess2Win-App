package com.app.app.ui

import android.os.Bundle
import com.app.app.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.app.app.R
import com.core.base.BaseActivity
import com.app.app.BR

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun bindingVariable(): Int {
        return BR.viewModel
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun controllerId(): Int {
        return 0
    }

    override fun getViewModel(): MainViewModel {
        return mainViewModel
    }
}
