package com.feature.help.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.base.BaseViewHolder
import com.feature.help.databinding.HelpItemBinding
import com.feature.help.ui.model.HelpItemModel

class HelpListAdapter(var helpItemsList: ArrayList<HelpItemModel>) :
    RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return PrizesListHolder(
            HelpItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int = helpItemsList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class PrizesListHolder(var binding: HelpItemBinding) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.model = helpItemsList[position]
        }
    }
}