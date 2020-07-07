package com.justmsme.udyam.ui.home

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.justmsme.udyam.R
import com.justmsme.udyam.data.services

class HomeViewModel : ViewModel() {
    val servicesList = services

    fun onServiceClick(v: View){
        v.findNavController().navigate(R.id.action_HomeFragment_to_HomeSecondFragment)
    }
}