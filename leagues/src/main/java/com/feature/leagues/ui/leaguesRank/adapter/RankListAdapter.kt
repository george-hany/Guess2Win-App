package com.feature.leagues.ui.leaguesRank.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.base.BaseViewHolder
import com.feature.leagues.databinding.LeagueRankItemBinding
import com.feature.leagues.ui.leaguesRank.model.RankItemUIModel

class RankListAdapter(var ranksList: ArrayList<RankItemUIModel>) :
    RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return RanksListHolder(
            LeagueRankItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int = ranksList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class RanksListHolder(var binding: LeagueRankItemBinding) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.model = ranksList[position]
        }
    }
}