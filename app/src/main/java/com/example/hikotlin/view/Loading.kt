package com.example.hikotlin.view

import android.app.Activity
import android.app.AlertDialog
import com.example.hikotlin.R

class Loading(private val activity: Activity) {
    private lateinit var dialog: AlertDialog

    fun startLoading() {
        val inflater = activity.layoutInflater
        val dialogView = inflater.inflate(R.layout.loading_item, null)

//        Glide.with(activity).load(R.drawable.cat_loading).into(findViewById(R.id.imageView_catLoading))

        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        builder.setCancelable(false)

        dialog = builder.create()
        dialog.show()
    }

    fun dismissLoading() {
        dialog.dismiss()
    }


}