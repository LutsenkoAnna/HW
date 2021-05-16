package com.example.hw.favorite

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw.Data.favoriteMovies
import com.example.hw.Data.movies
import com.example.hw.R

class FavoriteActivity : AppCompatActivity() {
    private val recycler by lazy { findViewById<RecyclerView>(R.id.recyclerView)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        initRecycler()
    }

    private fun initRecycler() {
        val orientation = resources.configuration.orientation
        //настройка отображения экрана (для портретной список в 2 колонки)
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
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

        //добавление в список избранного
        for (item in movies) {
            if (item in favoriteMovies && !item.isFavorite) {
                favoriteMovies.remove(item)
            } else if (item.isFavorite && item !in favoriteMovies) {
                favoriteMovies.add(item)
            }
        }
        recycler.adapter = FavoriteAdapter(favoriteMovies)

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recycler.addItemDecoration(itemDecoration)
    }
}