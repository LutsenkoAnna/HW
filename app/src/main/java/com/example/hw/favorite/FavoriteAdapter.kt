package com.example.hw.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw.R
import com.example.hw.movie.MovieItem

class FavoriteAdapter(private val items:List<MovieItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    companion object {
        private const val VIEW_HEADER = 0
        private const val VIEW_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_HEADER) {
            FavoriteHeaderVH(inflater.inflate(R.layout.item_header_favorite, parent, false))
        } else {
            FavoriteVH(inflater.inflate(R.layout.item_favorite, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FavoriteVH) {
            val item = items[position - 1]
            holder.bind(item)
        }

    }

    override fun getItemCount() = items.size + 1 //+1 - header

    override fun getItemViewType(position: Int): Int = if (position == 0) VIEW_HEADER else VIEW_ITEM

}
