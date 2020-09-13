package com.feature.matches.ui.matches.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.base.BaseViewHolder
import com.feature.matches.databinding.MatchItemBinding
import com.feature.matches.ui.matches.model.MatchItemUIModel

class MatchesListAdapter(var matchesList: ArrayList<MatchItemUIModel>) :
    RecyclerView.Adapter<BaseViewHolder>() {
    lateinit var matchesListInterface: MatchesListInterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MatchesListHolder(
            MatchItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int = matchesList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
        holder.itemView.setOnClickListener {
            matchesListInterface.onClick(
                matchesList[position]
            )
        }
    }

    inner class MatchesListHolder(var binding: MatchItemBinding) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.model = matchesList[position]
        }
    }

    interface MatchesListInterface {
        fun onClick(match: MatchItemUIModel)
    }
}