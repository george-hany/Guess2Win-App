package com.feature.splash.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.core.base.BaseFragment
import com.core.utils.AppConstant.InterstitialId
import com.core.utils.AppConstant.loginRequest
import com.feature.login.ui.LoginFragment
import com.feature.splash.BR
import com.feature.splash.R
import com.feature.splash.databinding.FragmentSplashBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    val splashViewModel: SplashViewModel by viewModel()
    private lateinit var mInterstitialAd: InterstitialAd
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInterstitialAd()
    }

    private fun setupInterstitialAd() {
        mInterstitialAd = InterstitialAd(context)
        mInterstitialAd.adUnitId = InterstitialId
        mInterstitialAd.loadAd(
            AdRequest.Builder().addTestDevice("E30A665A4AA4D5D5C491A7A2F51A0BFE").build()
        )
        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
                navigateToUriWithClearStack(R.string.board)
                mInterstitialAd.loadAd(
                    AdRequest.Builder().addTestDevice("E30A665A4AA4D5D5C491A7A2F51A0BFE").build()
                )
            }
        }
    }

    override fun bindingVariable(): Int {
        return BR.viewModel
    }

    override fun layoutId(): Int {
        return R.layout.fragment_splash
    }

    override fun getViewModel(): SplashViewModel {
        return splashViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        splashTimeOutObservation()
    }

    private fun splashTimeOutObservation() {
        splashViewModel.splashTimeOut.observe(viewLifecycleOwner, Observer {
            if (splashViewModel.appLanguage.isNullOrEmpty()) {
                navigateToUriWithClearStack(R.string.chooseLanguage)
            } else if (splashViewModel.appTheme.isNullOrEmpty()) {
                navigateToUriWithClearStack(R.string.chooseTheme)
            } else if (!splashViewModel.isLoggedIn) {
                activity?.startActivityForResult(
                    Intent(context, LoginFragment::class.java),
                    loginRequest
                )
            } else {
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                } else {
                    navigateToUriWithClearStack(R.string.board)
                }
            }
        })
    }
}
