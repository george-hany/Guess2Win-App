package com.feature.rank.ui.rankContainer.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.feature.rank.R
import com.feature.rank.ui.rank.RankFragment
import com.feature.rank.ui.rank.model.RankType

private val TAB_TITLES = arrayOf(
    R.string.week,
    R.string.month,
    R.string.year
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(
    private val context: Context,
    fm: FragmentManager
) :
    FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when (position) {
            0 -> RankFragment.newInstance(RankType.WEEK)
            1 -> RankFragment.newInstance(RankType.MONTH)
            2 -> RankFragment.newInstance(RankType.YEAR)
            else -> RankFragment.newInstance(RankType.WEEK)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }
}