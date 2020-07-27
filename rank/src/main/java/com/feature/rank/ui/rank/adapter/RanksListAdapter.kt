package com.feature.rank.ui.rank.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.base.BaseViewHolder
import com.feature.rank.R
import com.feature.rank.databinding.RankItemBinding
import com.feature.rank.ui.rank.model.RanksItemUIModel

class RanksListAdapter(
    var ranksList: ArrayList<RanksItemUIModel>,
    var userId: String?
) :
    RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return RanksListHolder(
            RankItemBinding.inflate(
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

    inner class RanksListHolder(var binding: RankItemBinding) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.model = ranksList[position]
            binding.index.text = "${position + 1}-"
            if (userId == ranksList[position].id)
                binding.container.setBackgroundResource(R.color.greyLight_duskNight)
        }
    }
}