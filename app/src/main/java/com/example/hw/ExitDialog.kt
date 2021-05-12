package com.example.hw

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View

class ExitDialog(context: Context, private val activity: Activity) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_exit)

        findViewById<View>(R.id.btnYes).setOnClickListener{
            activity.finish()
        }

        findViewById<View>(R.id.btnNo).setOnClickListener {
            dismiss()
        }
    }
}