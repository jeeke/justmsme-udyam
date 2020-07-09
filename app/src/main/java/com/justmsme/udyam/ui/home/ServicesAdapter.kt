//package com.justmsme.udyam.ui.home
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.navigation.findNavController
//import androidx.recyclerview.widget.RecyclerView
//import com.justmsme.udyam.MainActivity
//import com.justmsme.udyam.databinding.HolderServiceBinding

//class ServicesAdapter() : RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder>() {
//
//    private val itemList = MainActivity.services()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
//        ServiceViewHolder(
//            HolderServiceBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//        )
//
//    override fun getItemCount() = itemList.size
//
//    override fun onBindViewHolder(
//        holder: ServiceViewHolder,
//        position: Int
//    ) {
//        val serviceItem = itemList[position]
//        holder.binding.service = serviceItem
//        holder.binding.root.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeFragmentToHomeSecondFragment(serviceItem.formUrl)
//            it.findNavController().navigate(action)
//        }
//    }
//
//    inner class ServiceViewHolder(
//        val binding: HolderServiceBinding
//    ) : RecyclerView.ViewHolder(binding.root)
//
//}

