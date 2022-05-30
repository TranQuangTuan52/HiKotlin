package com.example.hikotlin.model

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context

object CustomAlert {
    private var dialog: Dialog? = null
    private var builder: AlertDialog.Builder? = null
    fun show(context: Context, title: String, message: String) {
        builder = AlertDialog.Builder(context)
        builder?.setCancelable(false)
        builder?.setTitle(title)
        builder?.setMessage(message)
        builder?.setPositiveButton("Confirm") { dialog, _ ->
            dismiss()
        }
        dialog = builder?.create()
        dialog?.show()
    }

    private fun dismiss() {
        dialog?.dismiss()
    }
}