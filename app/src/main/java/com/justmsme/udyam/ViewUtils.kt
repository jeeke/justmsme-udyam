package com.justmsme.udyam

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


fun Context?.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}


fun Context.showDialog(
    message: String,
    positiveButtonTitle: String,
    negativeButtonTitle: String,
    positiveButton: () -> Unit,
    negativeButton: () -> Unit
) {
    MaterialAlertDialogBuilder(this)
//        .setTitle(message)
        .setMessage(message)
        .setPositiveButton(positiveButtonTitle) { dialogInterface: DialogInterface, _: Int ->
            positiveButton()
            dialogInterface.dismiss()
        }
        .setNegativeButton(negativeButtonTitle) { dialogInterface: DialogInterface, _: Int ->
            negativeButton()
            dialogInterface.dismiss()
        }
        .show()
}

//fun TextView.setHtml(html: String) {
//    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//        Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT);
//    } else {
//        Html.fromHtml("<h2>Title</h2><br><p>Description here</p>");
//    }
//}