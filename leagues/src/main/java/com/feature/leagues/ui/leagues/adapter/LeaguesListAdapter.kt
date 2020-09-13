package com.feature.leagues.ui.leagues.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.base.BaseViewHolder
import com.feature.leagues.databinding.LeagueItemBinding
import com.feature.leagues.ui.leagues.model.LeaguesItemUIModel

class LeaguesListAdapter(var leaguesList: ArrayList<LeaguesItemUIModel>) :
    RecyclerView.Adapter<BaseViewHolder>() {
    lateinit var leaguesListInterface: LeaguesInterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return LeaguesListHolder(
            LeagueItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int = leaguesList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
        holder.itemView.setOnClickListener {
            leaguesListInterface.onClick(
                leaguesList[position]
            )
        }
    }

    inner class LeaguesListHolder(var binding: LeagueItemBinding) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.model = leaguesList[position]
        }
    }

    interface LeaguesInterface {
        fun onClick(model: LeaguesItemUIModel)
    }
}