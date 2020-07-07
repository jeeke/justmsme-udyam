package com.justmsme.udyam.ui.contact

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.justmsme.udyam.R
import com.justmsme.udyam.showDialog
import com.justmsme.udyam.toast
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment : Fragment() {

    companion object {
        fun newInstance() = ContactFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageView8.setOnClickListener { mailUs() }
        imageView7.setOnClickListener { callUs() }
        imageView9.setOnClickListener { copyMessage("18001216896", "Phone number") }
        imageView10.setOnClickListener { copyMessage("info@justmsme.com", "Email") }
    }

    private fun callUs() {
        context?.showDialog(
            "Are you sure you want to call us?",
            "Yes",
            "Cancel",
            {
                Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:18001216896")
                    startActivity(this)
                }
            },
            {}
        )

    }

    private fun mailUs() {
        context?.showDialog(
            "Are you sure you want to mail us?",
            "Yes",
            "Cancel",
            {
                try {
                    Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:") // only email apps should handle this
                        putExtra(Intent.EXTRA_EMAIL, arrayOf("info@justmsme.com"))
                        putExtra(Intent.EXTRA_SUBJECT, "")
                        startActivity(this)
                    }
                } catch (e: Exception) {
                    Log.v("ContactFragment", e.message.toString())
                }
            },
            {}
        )
    }

    private fun copyMessage(message: String, toast: String): Boolean {
        val clipboard: ClipboardManager =
            context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("message", message)
        clipboard.setPrimaryClip(clip)
        context.toast("$toast copied")
        return true
    }

}