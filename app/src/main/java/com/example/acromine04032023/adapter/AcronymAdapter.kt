package com.example.acromine04032023.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.acromine04032023.R
import com.example.acromine04032023.data.models.Lf
import com.example.acromine04032023.databinding.LongFormItemBinding

class AcronymAdapter(
    private val meaningList: MutableList<Lf> = mutableListOf()
): RecyclerView.Adapter<AcronymAdapter.AcronymViewHolder>() {

    fun updateMeanings(newData: List<Lf>) {
        val callback = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = meaningList.size

            override fun getNewListSize(): Int = newData.size


            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                meaningList[oldItemPosition].lf == newData[newItemPosition].lf

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                meaningList[oldItemPosition].lf?.equals(newData[newItemPosition].lf) ?: false

            override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
                return super.getChangePayload(oldItemPosition, newItemPosition)
            }
        }

        val result = DiffUtil.calculateDiff(callback)

        meaningList.clear()
        meaningList.addAll(newData)

        result.dispatchUpdatesTo(this)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymViewHolder {
        return AcronymViewHolder(
            LongFormItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AcronymViewHolder, position: Int) {
        holder.bind(meaningList[position])
    }

    override fun getItemCount(): Int = meaningList.size

    class AcronymViewHolder (
       private val binding: LongFormItemBinding
            ): RecyclerView.ViewHolder(binding.root){

        fun bind(meaning: Lf) {
            binding.tvMeaning.text = meaning.lf ?: "NOT AVAILABLE"
        }
    }
}