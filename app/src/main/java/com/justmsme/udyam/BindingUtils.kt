package com.justmsme.udyam

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:icon")
fun setIcon(view: ImageView, iconResId: Int) {
    view.setImageResource(iconResId)
}