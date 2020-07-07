package com.justmsme.udyam

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:icon")
fun setIcon(view: ImageView, iconResId: Int) {
    view.setImageResource(iconResId)
}

@BindingAdapter("app:html")
fun setHtml(view: TextView, html: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        view.text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT);
    } else {
        view.text = Html.fromHtml(html);
    }
}