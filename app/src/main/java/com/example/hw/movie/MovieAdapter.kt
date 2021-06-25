package com.example.hw.movie

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw.R

class MovieAdapter(private val items:List<MovieItem>, private val clickListener: MovieListFragment.OnMovieClickListener?)
    : RecyclerView.Adapter<MovieVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        return MovieVH(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
            val item = items[position]
            holder.bind(item)

            //детали фильма
            holder.image.setOnClickListener {
                clickListener?.onDetailsClick(item)
            }

            if (item.isFavorite) {
                holder.imageBtnFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                holder.imageBtnFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }

            if(item.isVisited) {
                holder.name.setTextColor(Color.BLACK)
            } else {
                holder.name.setTextColor(Color.GRAY)
            }

            //добавить в избранное
            holder.imageBtnFavorite.setOnClickListener {
                clickListener?.onAddFavoriteClick(item)
            }
    }

    override fun getItemCount() = items.size

    interface MovieClickListener {
        fun onDetailsClick(item: MovieItem) {}
        fun onAddFavoriteClick(item: MovieItem) {}
    }
}