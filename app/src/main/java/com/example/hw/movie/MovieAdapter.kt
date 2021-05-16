package com.example.hw.movie

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw.R

class MovieAdapter(private val items:List<MovieItem>, private val clickListener: MovieClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val VIEW_HEADER = 0
        private const val VIEW_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_HEADER) {
            MovieHeaderVH(inflater.inflate(R.layout.item_header, parent, false))
        } else {
            MovieVH(inflater.inflate(R.layout.item_movie, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieVH) {
            val item = items[position - 1]
            holder.bind(item)

            //кнопка "Детали"
            holder.detailsBtn.setOnClickListener {
                clickListener.onDetailsClick(item)
            }

            if (item.isFavorite) {
                holder.imageBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                holder.imageBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }

            if(item.isVisited) {
                holder.name.setTextColor(Color.BLACK)
            } else {
                holder.name.setTextColor(Color.GRAY)
            }

            //добавить в избранное
            holder.imageBtn.setOnClickListener {
                clickListener.onFavoriteClick(item)
            }

        } else if (holder is MovieHeaderVH) {
            //кнопка перехода в список избранного
            holder.favoriteBtn.setOnClickListener {
                clickListener.onFavoriteListClick()
            }

            //кнопка пригласить друга
            holder.inviteBtn.setOnClickListener {
                clickListener.onInviteClick()
            }
        }
    }

    override fun getItemCount() = items.size + 1 //+1 - header

    override fun getItemViewType(position: Int): Int = if (position == 0) VIEW_HEADER else VIEW_ITEM

    interface MovieClickListener {
        fun onDetailsClick(item: MovieItem) {}
        fun onFavoriteClick(item: MovieItem) {}
        fun onFavoriteListClick() {}
        fun onInviteClick() {}
    }
}