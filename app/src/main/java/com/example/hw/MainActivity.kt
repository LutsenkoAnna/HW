package com.example.hw

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw.Data.movies
import com.example.hw.details.DetailsActivity
import com.example.hw.details.DetailsData
import com.example.hw.details.ResultData
import com.example.hw.favorite.FavoriteActivity
import com.example.hw.movie.MovieAdapter
import com.example.hw.movie.MovieItem
import android.content.res.Configuration.ORIENTATION_PORTRAIT

class MainActivity : AppCompatActivity() {
    private val recycler by lazy { findViewById<RecyclerView>(R.id.recyclerView)  }
    companion object {
        const val REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
    }

    private fun initRecycler() {
        val orientation = resources.configuration.orientation
        //настройка отображения экрана (для портретной список в 2 колонки)
        if (orientation == ORIENTATION_PORTRAIT) {
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recycler.layoutManager = layoutManager
        } else {
            val layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int) =
                    if (position > 0) 1 else 2
            }
            recycler.layoutManager = layoutManager
        }

        val intentDetails = Intent(this, DetailsActivity::class.java)
        val intentFavorite = Intent(this, FavoriteActivity::class.java)
        recycler.adapter = MovieAdapter(movies, object : MovieAdapter.MovieClickListener {

            //кнопка "Детали"
            override fun onDetailsClick(item: MovieItem) {
                item.isVisited = true
                recycler.adapter?.notifyDataSetChanged()
                intentDetails.putExtra(
                        DetailsActivity.EXTRA_DATA,
                        DetailsData(item.name, item.details, item.image)
                )
                startActivityForResult(intentDetails, REQUEST_CODE)
            }

            //добавить в избранное
            override fun onFavoriteClick(item: MovieItem) {
                item.isFavorite = !item.isFavorite
                recycler.adapter?.notifyDataSetChanged()
            }

            //список избранного
            override fun onFavoriteListClick() {
                startActivityForResult(intentFavorite, REQUEST_CODE)
            }

            //пригласить друга
            override fun onInviteClick() {
                val share = Intent.createChooser(Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(
                            Intent.EXTRA_TEXT,
                            getString(R.string.inviteMessage) + "https://play.google.com/store/apps/details?id=$packageName"
                    ) }, null)
                startActivity(share)
            }
        })

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recycler.addItemDecoration(itemDecoration)
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

    //Диалог на кнопку Back
    override fun onBackPressed() {
        val dialog: Dialog = ExitDialog(this,this)
        dialog.show()
    }
}
