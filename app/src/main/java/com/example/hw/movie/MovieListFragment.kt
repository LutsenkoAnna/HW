package com.example.hw.movie

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw.Data
import com.example.hw.R

class MovieListFragment(private val items: MutableList<MovieItem>) : Fragment() {
    companion object { const val TAG = "MovieListFragment" }

    var listener: OnMovieClickListener? = null
    lateinit var recycler: RecyclerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity is OnMovieClickListener) {
            listener = activity as OnMovieClickListener
        } else {
            throw Exception("Activity must implement OnMovieClickListener")
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMovieClickListener) {
            var movielistener = context as OnMovieClickListener
            listener = object : MovieAdapter.MovieClickListener, OnMovieClickListener {

                //кнопка "Детали"
                override fun onDetailsClick(item: MovieItem) {
                    movielistener.onDetailsClick(item)
                    item.isVisited = true
                    recycler.adapter?.notifyDataSetChanged()
                }

                //добавить в избранное
                override fun onAddFavoriteClick(item: MovieItem) {
                    item.isFavorite = !item.isFavorite
                    movielistener.onAddFavoriteClick(item)
                    for (item in Data.movies) {
                        if (item in Data.favoriteMovies && !item.isFavorite) {
                            Data.favoriteMovies.remove(item)
                        } else if (item.isFavorite && item !in Data.favoriteMovies) {
                            Data.favoriteMovies.add(item)
                        }
                    }
                    recycler.adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val orientation = resources.configuration.orientation
        recycler = view.findViewById(R.id.recyclerView)

        //настройка отображения экрана (для портретной список в 2 колонки)
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            val layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            recycler.layoutManager = layoutManager
        } else {
            val layoutManager = GridLayoutManager(view.context, 2, LinearLayoutManager.VERTICAL, false)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int) =
                        if (position > 0) 1 else 2
            }
            recycler.layoutManager = layoutManager
        }
        recycler.adapter = MovieAdapter(items, listener)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnMovieClickListener {
        fun onDetailsClick(item: MovieItem)
        fun onAddFavoriteClick(item: MovieItem)
    }
}