package com.justmsme.udyam.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.justmsme.udyam.R
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapterExample(private val context: Context) :
    SliderViewAdapter<SliderAdapterExample.SliderVH>() {

    private val itemList = arrayListOf(
        R.drawable.header_two,
        R.drawable.header_one
    )

    override fun onCreateViewHolder(parent: ViewGroup): SliderVH? {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_slider_layout_item, null)
        return SliderVH(itemView)
    }

    override fun onBindViewHolder(viewHolder: SliderVH?, position: Int) {
        val sliderItem = itemList[position]
        (viewHolder?.itemView as? ImageView)?.setImageResource(sliderItem)
        viewHolder?.itemView?.setOnClickListener {
            Toast.makeText(context, "This is item in position $position", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun getCount(): Int { //slider view count could be dynamic size
        return itemList.size
    }

    class SliderVH(itemView: View) : SliderViewAdapter.ViewHolder(itemView)
}