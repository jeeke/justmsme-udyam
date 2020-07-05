package com.justmsme.udyam.ui.faqs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.justmsme.udyam.data.Faq
import com.justmsme.udyam.databinding.HolderFaqBinding
import com.justmsme.udyam.hide
import com.justmsme.udyam.show

class FaqsAdapter(
    private val itemList: ArrayList<Faq> = ArrayList()
) : RecyclerView.Adapter<FaqsAdapter.FaqViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FaqViewHolder(
        HolderFaqBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(
        holder: FaqViewHolder,
        position: Int
    ) {
        holder.binding.faq = itemList[position]
        holder.binding.question.setOnClickListener {
            if (holder.isExpanded) {
                holder.binding.answer.hide()
            } else {
                holder.binding.answer.show()
            }
            holder.isExpanded = holder.isExpanded.not()
        }
    }

    inner class FaqViewHolder(val binding: HolderFaqBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var isExpanded = false
    }

}