package com.justmsme.udyam.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.justmsme.udyam.R
import com.justmsme.udyam.hide
import com.justmsme.udyam.toast
import kotlinx.android.synthetic.main.fragment_home_second.*

class HomeSecondFragment : Fragment() {

    private val args: HomeSecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_second, container, false)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        form.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView?,
                errorCode: Int,
                description: String?,
                failingUrl: String?
            ) {
                super.onReceivedError(view, errorCode, description, failingUrl)
                context.toast("On no! $description")
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar?.hide()
            }
        }
        form.settings.javaScriptEnabled = true
        form.loadUrl("https://forms.justmsme.com/registerinmsme/form/GSTFILING/formperma/s_kaTURw9fSEdVqNMbQslJXt5ER8nLQk0T-0w2zUUpE?zf_rszfm=1")
    }
}