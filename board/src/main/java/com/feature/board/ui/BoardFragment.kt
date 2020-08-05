package com.feature.board.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.core.base.BaseFragment
import com.core.utils.AppConstant
import com.core.utils.AppConstant.ADMOB_APP_ID
import com.core.utils.CommonUtils.getAdRequest
import com.core.utils.CommonUtils.getInterstitialAd
import com.core.utils.CommonUtils.getRewardedVideoAd
import com.core.utils.InterstitialAdManager
import com.facebook.login.LoginManager
import com.feature.board.BR
import com.feature.board.R
import com.feature.board.databinding.FragmentBoardBinding
import com.feature.board.databinding.LogoutConfirmDialogBinding
import com.feature.contactus.ui.ContactUsFragment
import com.feature.extrapoints.ui.ExtraPointsFragment
import com.feature.help.ui.HelpFragment
import com.feature.leagues.ui.leagues.LeaguesFragment
import com.feature.matches.ui.matches.MatchesFragment
import com.feature.prizes.ui.PrizesFragment
import com.feature.profile.ui.ProfileFragment
import com.feature.rank.ui.RankContainerFragment
import com.feature.settings.ui.SettingsFragment
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardedVideoAd
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class BoardFragment : BaseFragment<FragmentBoardBinding, BoardViewModel>(),
    SettingsFragment.CallBack, ExtraPointsFragment.CallBack {
    private val boardViewModel: BoardViewModel by viewModel()
    private var positionSelected = 1
    private var activeFragment: Fragment? = null
    private var matchesFragment: MatchesFragment? = null
    private var leaguesFragment: LeaguesFragment? = null
    private var rankContainerFragment: RankContainerFragment? = null
    private var profileFragment: ProfileFragment? = null
    private var settingsFragment: SettingsFragment? = null
    private var extraPointsFragment: ExtraPointsFragment? = null
    private var prizesFragment: PrizesFragment? = null
    private var helpFragment: HelpFragment? = null
    private var contactUsFragment: ContactUsFragment? = null
    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var interstitialAdManager: InterstitialAdManager
    lateinit var rewardedVideoAd: RewardedVideoAd
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInterstitialAd()
        MobileAds.initialize(context, ADMOB_APP_ID)
        interstitialAdManager = InterstitialAdManager(requireContext())
        rewardedVideoAd = getRewardedVideoAd(requireContext())
    }

    private fun setupInterstitialAd() {
        mInterstitialAd = getInterstitialAd(requireContext())
        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdOpened() {
                super.onAdOpened()
                interstitialAdManager.stopInterstitialAd()
            }

            override fun onAdClosed() {
                super.onAdClosed()
                openRankFragment(3)
                setupInterstitialAd()
                interstitialAdManager.startInterstitialAd()
            }
        }
    }

    private lateinit var dialogInfo: Dialog
    private lateinit var dialogBinding: LogoutConfirmDialogBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdView()
        dialogInfo = Dialog(requireContext())
        dialogInfo.requestWindowFeature(Window.FEATURE_NO_TITLE)
        handlingBottomBarClicks(positionSelected)
        mViewModel.sideBarClickPosition.observe(viewLifecycleOwner, Observer {
            handlingBottomBarClicks(it)
        })
        interstitialAdManager.startInterstitialAd()
    }

    private fun setupAdView() {
        viewDataBinding.adView.run {
            loadAd(getAdRequest())
        }
    }

    private fun handlingBottomBarClicks(position: Int) {
        viewDataBinding.run {
            when (position) {
                1 -> {
                    makePositionSelectedToUnSelected(positionSelected)
                    matches.setBackgroundResource(R.color.shapes)
                    matches.setImageResource(R.drawable.lc_ball_selected)
                    positionSelected = position
                    matchesFragment?.let {
                        replaceExistFragment(it)
                    } ?: kotlin.run {
                        matchesFragment = MatchesFragment()
                        replaceNewFragment(matchesFragment!!)
                    }
                }
                2 -> {
                    makePositionSelectedToUnSelected(positionSelected)
                    leagues.setBackgroundResource(R.color.shapes)
                    leagues.setImageResource(R.drawable.ic_shield_selected)
                    positionSelected = position
                    leaguesFragment?.let {
                        replaceExistFragment(it)
                    } ?: kotlin.run {
                        leaguesFragment = LeaguesFragment()
                        replaceNewFragment(leaguesFragment!!)
                    }
                }
                3 -> {

                    if (mInterstitialAd.isLoaded) {
                        mInterstitialAd.show()
                    } else {
                        openRankFragment(position)
                    }
                }
                4 -> {
                    makePositionSelectedToUnSelected(positionSelected)
                    extraPoints.setBackgroundResource(R.color.shapes)
                    extraPoints.setImageResource(R.drawable.ic_video_selected)
                    positionSelected = position
                    extraPointsFragment?.let {
                        replaceExistFragment(it)
                    } ?: kotlin.run {
                        extraPointsFragment =
                            ExtraPointsFragment.newInstance(this@BoardFragment, rewardedVideoAd)
                        replaceNewFragment(extraPointsFragment!!)
                    }
                }
                5 -> {
                    makePositionSelectedToUnSelected(positionSelected)
                    profile.setBackgroundResource(R.color.shapes)
                    profile.setImageResource(R.drawable.ic_profile_selected)
                    positionSelected = position
                    profileFragment?.let {
                        replaceExistFragment(it)
                    } ?: kotlin.run {
                        profileFragment = ProfileFragment()
                        replaceNewFragment(profileFragment!!)
                    }
                }
                6 -> {
                    makePositionSelectedToUnSelected(positionSelected)
                    settings.setBackgroundResource(R.color.shapes)
                    settings.setImageResource(R.drawable.ic_settings_selected)
                    positionSelected = position
                    settingsFragment?.let {
                        replaceExistFragment(it)
                    } ?: kotlin.run {
                        settingsFragment = SettingsFragment.newInstance(this@BoardFragment)
                        replaceNewFragment(settingsFragment!!)
                    }
                }
                7 -> {
                    makePositionSelectedToUnSelected(positionSelected)
                    prizes.setBackgroundResource(R.color.shapes)
                    prizes.setImageResource(R.drawable.ic_giveaway_selected)
                    positionSelected = position
                    prizesFragment?.let {
                        replaceExistFragment(it)
                    } ?: kotlin.run {
                        prizesFragment = PrizesFragment()
                        replaceNewFragment(prizesFragment!!)
                    }
                }
                8 -> {
                    makePositionSelectedToUnSelected(positionSelected)
                    help.setBackgroundResource(R.color.shapes)
                    help.setImageResource(R.drawable.ic_help_selected)
                    positionSelected = position
                    helpFragment?.let {
                        replaceExistFragment(it)
                    } ?: kotlin.run {
                        helpFragment = HelpFragment()
                        replaceNewFragment(helpFragment!!)
                    }
                }
                9 -> {
                    makePositionSelectedToUnSelected(positionSelected)
                    contactUs.setBackgroundResource(R.color.shapes)
                    contactUs.setImageResource(R.drawable.ic_contact_selected)
                    positionSelected = position
                    contactUsFragment?.let {
                        replaceExistFragment(it)
                    } ?: kotlin.run {
                        contactUsFragment = ContactUsFragment()
                        replaceNewFragment(contactUsFragment!!)
                    }
                }
                10 -> {
                    openConfirmationDialog()
                }
            }
        }
    }

    private fun openRankFragment(position: Int) {
        viewDataBinding.run {
            makePositionSelectedToUnSelected(positionSelected)
            rank.setBackgroundResource(R.color.shapes)
            rank.setImageResource(R.drawable.ic_rank_selected)
            positionSelected = position
            rankContainerFragment?.let {
                replaceExistFragment(it)
            } ?: kotlin.run {
                rankContainerFragment = RankContainerFragment()
                replaceNewFragment(rankContainerFragment!!)
            }
        }
    }

    private fun makePositionSelectedToUnSelected(position: Int) {
        viewDataBinding.run {
            when (position) {
                1 -> {
                    matches.background = null
                    matches.setImageResource(R.drawable.ic_ball)
                }
                2 -> {
                    leagues.background = null
                    leagues.setImageResource(R.drawable.ic_shield)
                }
                3 -> {
                    rank.background = null
                    rank.setImageResource(R.drawable.ic_rank)
                }
                4 -> {
                    extraPoints.background = null
                    extraPoints.setImageResource(R.drawable.ic_video)
                }
                5 -> {
                    profile.background = null
                    profile.setImageResource(R.drawable.ic_profile)
                }
                6 -> {
                    settings.background = null
                    settings.setImageResource(R.drawable.ic_settings)
                }
                7 -> {
                    prizes.background = null
                    prizes.setImageResource(R.drawable.ic_giveaway)
                }
                8 -> {
                    help.background = null
                    help.setImageResource(R.drawable.ic_help)
                }
                9 -> {
                    contactUs.background = null
                    contactUs.setImageResource(R.drawable.ic_contact)
                }
                10 -> {
                }
            }
        }
    }

    private fun replaceExistFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().hide(activeFragment!!)
            .show(fragment)
            .commit()
        activeFragment = fragment
    }

    private fun replaceNewFragment(fragment: Fragment) {
        activeFragment?.let {
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.nav_host_fragment, fragment, fragment.tag).hide(it).commit()
        } ?: kotlin.run {
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.nav_host_fragment, fragment, fragment.tag).commit()
        }
        activeFragment = fragment
    }

    private fun openConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
        dialogInfo.setCancelable(false)
        dialogBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.logout_confirm_dialog,
                null,
                false
            )
        builder.setView(dialogBinding.root)
        dialogInfo = builder.create()
        dialogBinding.logout.setOnClickListener {
            dialogInfo.dismiss()
            confirmLogout()
            navigateToUriWithClearStack(R.string.splash)
        }
        dialogBinding.cancel.setOnClickListener {
            dialogInfo.dismiss()
        }
        dialogInfo.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialogInfo.show()
    }

    private fun confirmLogout() {
        LoginManager.getInstance().logOut()
        boardViewModel.setIsLoggedInToFalse()
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_board

    override fun getViewModel(): BoardViewModel = boardViewModel

    override fun onDestroy() {
        super.onDestroy()
        Log.e("destroyy", "true")
        interstitialAdManager.stopInterstitialAd()
        matchesFragment?.let {
            matchesFragment = null
        }
    }

    override fun onChangeTheme(themeType: String) {
        boardViewModel.saveTheme(themeType)
        if (themeType == AppConstant.NIGHT) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        baseActivity?.recreate()
    }

    override fun onVideoAdOpened() {
        interstitialAdManager.stopInterstitialAd()
    }

    override fun onVideoAdClosed() {
        interstitialAdManager.startInterstitialAd()
    }

    override fun handleError() {}
}
