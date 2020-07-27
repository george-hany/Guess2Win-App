package com.feature.leagues.ui.leaguesDetails.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.feature.leagues.R
import com.feature.leagues.ui.leaguesRank.LeaguesRankFragment
import com.feature.matches.ui.matches.MatchesFragment
import com.feature.matches.ui.matches.model.MatchesFragmentModel
import com.feature.matches.ui.matches.model.MatchesType

private val TAB_TITLES = arrayOf(
    R.string.matches,
    R.string.rank
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(
    private val context: Context,
    fm: FragmentManager,
    var leagueId: String
) :
    FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when (position) {
            0 -> MatchesFragment.newInstance(MatchesFragmentModel(MatchesType.LEAGUE, leagueId))
            1 -> LeaguesRankFragment.newInstance(leagueId)
            else -> MatchesFragment.newInstance(MatchesFragmentModel(MatchesType.LEAGUE, leagueId))
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return TAB_TITLES.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }
}