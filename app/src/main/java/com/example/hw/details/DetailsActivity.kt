package com.example.hw.details

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.hw.R

class DetailsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
        const val EXTRA_DATA_RESULT = "EXTRA_DATA_RESULT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        intent.getParcelableExtra<DetailsData>(EXTRA_DATA)?.let {
            findViewById<TextView>(R.id.name).text = it.name
            findViewById<TextView>(R.id.details).text = it.details
            findViewById<ImageView>(R.id.image).setImageResource(it.image)
        }

        //Отправить результаты чекбокса и комментария
        findViewById<View>(R.id.sendBtn).setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(
                        EXTRA_DATA_RESULT,
                        ResultData(findViewById<CheckBox>(R.id.checkBox).isChecked, findViewById<EditText>(R.id.comment).text.toString())
                )
            })
            finish()
        }

    }
}