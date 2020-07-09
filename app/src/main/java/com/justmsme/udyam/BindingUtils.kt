package com.justmsme.udyam

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

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

@BindingAdapter("app:image_url")
fun loadImage(view: ImageView, url: String?) {
    if (url != null) {
        Glide.with(view)
            .load(url)
            .into(view)
    } else {
        view.setImageResource(R.drawable.header_two)
    }
}

@BindingAdapter("app:icon")
fun loadIcon(view: ImageView, url: String?) {
    if (url != null) {
        Glide.with(view)
            .load(url)
            .into(view)
    } else {
        view.setImageResource(R.drawable.ic_question)
    }
}