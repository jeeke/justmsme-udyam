package com.justmsme.udyam.ui.home

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.justmsme.udyam.MainActivity
import com.justmsme.udyam.R
import com.justmsme.udyam.data.ServiceItem

class HomeViewModel : ViewModel() {
    val servicesList = MainActivity.services()

    fun onServiceClick(v: View) {
        val serviceItem = when (v.id) {
            R.id.include1 -> servicesList[0]
            R.id.include2 -> servicesList[1]
            R.id.include3 -> servicesList[2]
            R.id.include4 -> servicesList[3]
            else -> servicesList[0]
        }
        val action =
            HomeFragmentDirections.actionHomeFragmentToHomeSecondFragment(serviceItem.formUrl)
        v.findNavController().navigate(action)
    }
}