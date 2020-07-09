package com.justmsme.udyam.ui.faqs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.justmsme.udyam.MainActivity
import com.justmsme.udyam.R
import kotlinx.android.synthetic.main.fragment_faqs.*

class FaqsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_faqs, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        faqsRecyclerView.apply {
            adapter = FaqsAdapter(MainActivity.faqs())
            layoutManager = LinearLayoutManager(context)
        }
    }
}