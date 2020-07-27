package com.feature.login.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.core.base.BaseActivity
import com.core.utils.AppConstant.loginSuccess
import com.facebook.*
import com.facebook.AccessToken
import com.facebook.login.LoginResult
import com.feature.login.BR
import com.feature.login.R
import com.feature.login.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseActivity<FragmentLoginBinding, LoginViewModel>() {

    val loginViewModel: LoginViewModel by viewModel()

    lateinit var callbackManager: CallbackManager

    override fun bindingVariable(): Int {
        return BR.viewModel
    }

    override fun layoutId(): Int {
        return R.layout.fragment_login
    }

    override fun getViewModel(): LoginViewModel {
        return loginViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginObservation()
        loginFinishObserver()
        viewDataBinding.loginButton.setReadPermissions(listOf("email", "public_profile"))
        callbackManager = CallbackManager.Factory.create()
        viewDataBinding.loginButton.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    loginViewModel.getUserProfile(AccessToken.getCurrentAccessToken())
                }

                override fun onCancel() {
                    // App code
                }

                override fun onError(exception: FacebookException) {
                    Log.d("API123", exception.message!!)
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loginFinishObserver() {
        loginViewModel.loginFinished.observe(this, Observer {
            if (it) {
                setResult(loginSuccess)
                finish()
            }
        })
    }

    private fun loginObservation() {
        loginViewModel.loginMediatorLiveData.observe(this, Observer {})
    }

    override fun controllerId(): Int = 0
}
