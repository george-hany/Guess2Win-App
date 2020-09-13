package com.feature.prizes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.base.BaseViewHolder
import com.feature.prizes.databinding.PrizeItemBinding
import com.feature.prizes.ui.model.PrizeUIModel

class PrizesAdapter(var prizesList: ArrayList<PrizeUIModel>) :
    RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return PrizesListHolder(
            PrizeItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int = prizesList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class PrizesListHolder(var binding: PrizeItemBinding) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.model = prizesList[position]
            binding.index.text = "${position + 1}-"
        }
    }
}