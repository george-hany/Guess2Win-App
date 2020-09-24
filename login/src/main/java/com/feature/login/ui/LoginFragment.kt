package com.feature.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.core.base.BaseActivity
import com.core.utils.AppConstant.loginSuccess
import com.core.utils.CommonUtils.getInterstitialAd
import com.facebook.*
import com.facebook.AccessToken
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.feature.login.BR
import com.feature.login.R
import com.feature.login.databinding.FragmentLoginBinding
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubInterstitial
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseActivity<FragmentLoginBinding, LoginViewModel>() {

    val loginViewModel: LoginViewModel by viewModel()
    private lateinit var mInterstitialAd: MoPubInterstitial
    var showAd = false
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
        setupLoginButtonEnvironment()
        checkUserLogging()
        setupInterstitialAd()
    }

    private fun checkUserLogging() {
        if (loginViewModel.isLoggedIn()) {
            showAd = true
            LoginManager.getInstance().logOut()
            LoginManager.getInstance()
                .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult?) {
                        loginViewModel.setIsLoading(true)
                        loginViewModel.getUserProfile(AccessToken.getCurrentAccessToken())
                    }

                    override fun onCancel() {}

                    override fun onError(exception: FacebookException?) {
                        showMessage(exception?.message ?: "")
                    }
                })
            LoginManager.getInstance()
                .logInWithReadPermissions(this, listOf("email", "public_profile"))
        }
    }

    private fun setupLoginButtonEnvironment() {
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
                    showMessage(exception.message!!)
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
                if (mInterstitialAd.isReady && showAd) {
                    mInterstitialAd.show()
                } else {
                    setResult(loginSuccess)
                    finish()
                }
            }
        })
    }

    private fun loginObservation() {
        loginViewModel.loginMediatorLiveData.observe(this, Observer {})
    }

    private fun setupInterstitialAd() {
        mInterstitialAd = getInterstitialAd(this)
        mInterstitialAd.interstitialAdListener = object : MoPubInterstitial.InterstitialAdListener {

            override fun onInterstitialLoaded(interstitial: MoPubInterstitial?) {}

            override fun onInterstitialFailed(
                interstitial: MoPubInterstitial?,
                errorCode: MoPubErrorCode?
            ) {
            }

            override fun onInterstitialShown(interstitial: MoPubInterstitial?) {}

            override fun onInterstitialClicked(interstitial: MoPubInterstitial?) {}

            override fun onInterstitialDismissed(interstitial: MoPubInterstitial?) {
                setResult(loginSuccess)
                finish()
            }
        }
        mInterstitialAd.load()
    }

    override fun onDestroy() {
        super.onDestroy()
        mInterstitialAd.destroy()
    }

    override fun controllerId(): Int = 0
}
