package com.justmsme.udyam.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.justmsme.udyam.MainActivity
import com.justmsme.udyam.databinding.ImageSliderLayoutItemBinding
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapterExample :
    SliderViewAdapter<SliderAdapterExample.SliderVH>() {

    private val itemList = MainActivity.sliderImages()

    override fun onCreateViewHolder(parent: ViewGroup): SliderVH? {
        return SliderVH(
            ImageSliderLayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: SliderVH, position: Int) {
        viewHolder.binding.url = itemList[position]
        viewHolder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToHomeSecondFragment(MainActivity.services()[0].formUrl)
            it.findNavController().navigate(action)
        }
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return itemList.size
    }

    class SliderVH(val binding: ImageSliderLayoutItemBinding) :
        SliderViewAdapter.ViewHolder(binding.root)
}