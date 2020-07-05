package com.justmsme.udyam.ui.process

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.justmsme.udyam.R

class ProcessFragment : Fragment() {

    private lateinit var galleryViewModel: ProcessViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
                ViewModelProvider(this).get(ProcessViewModel::class.java)
        return inflater.inflate(R.layout.fragment_process, container, false)
    }
}