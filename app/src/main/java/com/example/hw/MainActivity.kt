package com.example.hw

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TEXT_T = "EXTRA_TEXT_T"
        const val EXTRA_TEXT_G = "EXTRA_TEXT_G"
        const val EXTRA_TEXT_TS = "EXTRA_TEXT_TS"
        const val EXTRA_TEXT_S = "EXTRA_TEXT_S"
        const val REQUEST_CODE = 1
    }

    private val textViewTenet by lazy {
        findViewById<TextView>(R.id.nameTenet)
    }
    private val textViewGentelmen by lazy {
        findViewById<TextView>(R.id.nameGentelmen)
    }
    private val textViewTrial by lazy {
        findViewById<TextView>(R.id.nameTrial)
    }
    private val textViewSoul by lazy {
        findViewById<TextView>(R.id.nameSoul)
    }

    val nameTenet = "Довод"
    val detailsTenet = "Протагонист пытается обезвредить террориста с помощью уникальной технологии. Блокбастер-пазл Кристофера Нолана."
    val nameGentlemen = "Джентельмены"
    val detailsGentelmen = "Успешное возвращение Гая Ричи к корням — острая и живая криминальная комедия с блестящим актерским составом."
    val nameTrial = "Суд на Чикагской семеркой"
    val detailsTrial = "Чикаго, 1968 год. Демонстрация против войны во Вьетнаме вылилась в стычки с полицией. Семь участников беспорядков предстают пред судом по обвинениям в заговоре против американского правительства."
    val nameSoul = "Душа"
    val detailsSoul = "Джазмен хочет сбежать с того света на концерт. Фантазия о призвании и важных мелочах жизни от автора «Вверх»."


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Кнопка "Детали" для фильма Довод
        findViewById<View>(R.id.detailTenet).setOnClickListener() {
            //Изменение цвета названия
            textViewTenet.setTextColor(
                    ContextCompat.getColor(
                            applicationContext,
                            R.color.black
                    )
            )
            //Открытие "Деталей" фильма
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(
                    DetailsActivity.EXTRA_DATA,
                    MovieData(nameTenet, detailsTenet, R.drawable.tenet)
            )
            startActivityForResult(intent, REQUEST_CODE)
        }

        //Кнопка "Детали" для фильма Джентельмены
        findViewById<View>(R.id.detailGentelmen).setOnClickListener() {
            //Изменение цвета названия
            textViewGentelmen.setTextColor(
                    ContextCompat.getColor(
                            applicationContext,
                            R.color.black
                    )
            )
            //Открытие "Деталей" фильма
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(
                    DetailsActivity.EXTRA_DATA,
                    MovieData(nameGentlemen, detailsGentelmen, R.drawable.gentelmen)
            )
            startActivityForResult(intent, REQUEST_CODE)
        }

        //Кнопка "Детали" для фильма Суд над чикагской семеркой
        findViewById<View>(R.id.detailTrial).setOnClickListener() {
            //Изменение цвета названия
            textViewTrial.setTextColor(
                    ContextCompat.getColor(
                            applicationContext,
                            R.color.black
                    )
            )
            //Открытие "Деталей" фильма
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(
                    DetailsActivity.EXTRA_DATA,
                    MovieData(nameTrial, detailsTrial, R.drawable.trial)
            )
            startActivityForResult(intent, REQUEST_CODE)
        }

        //Кнопка "Детали" для фильма Душа
        findViewById<View>(R.id.detailSoul).setOnClickListener() {
            //Изменение цвета названия
            textViewSoul.setTextColor(
                    ContextCompat.getColor(
                            applicationContext,
                            R.color.black
                    )
            )
            //Открытие "Деталей" фильма
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(
                    DetailsActivity.EXTRA_DATA,
                    MovieData(nameSoul, detailsSoul, R.drawable.soul)
            )
            startActivityForResult(intent, REQUEST_CODE)
        }

        //Кнопка "Пригласить друга"
        findViewById<View>(R.id.invite).setOnClickListener {
            val share = Intent.createChooser(Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Hey! There are some cool movies https://play.google.com/store/apps/details?id=$packageName"
                )
            }, null)
            startActivity(share)
        }

        savedInstanceState?.getInt(EXTRA_TEXT_T)?.let {
            textViewTenet.setTextColor(it)
        }
        savedInstanceState?.getInt(EXTRA_TEXT_G)?.let {
            textViewGentelmen.setTextColor(it)
        }
        savedInstanceState?.getInt(EXTRA_TEXT_TS)?.let {
            textViewTrial.setTextColor(it)
        }
        savedInstanceState?.getInt(EXTRA_TEXT_S)?.let {
            textViewSoul.setTextColor(it)
        }
    }

    //Сохранение цвета при повороте экрана
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(EXTRA_TEXT_T, textViewTenet.currentTextColor)
        outState.putInt(EXTRA_TEXT_G, textViewGentelmen.currentTextColor)
        outState.putInt(EXTRA_TEXT_TS, textViewTrial.currentTextColor)
        outState.putInt(EXTRA_TEXT_S, textViewSoul.currentTextColor)
    }

    //Получение результатов (комментарий и лайк)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val resultData =
                        data?.getParcelableExtra<ResultData>(DetailsActivity.EXTRA_DATA_RESULT)
                resultData?.let {
                    Toast.makeText(this, "checkbox: ${it.checkBox} ; ${it.comment}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}