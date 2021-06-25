package com.example.hw.movie

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hw.R

class MovieVH(movieView: View) : RecyclerView.ViewHolder(movieView) {
    val name = movieView.findViewById<TextView>(R.id.name)
    val image = movieView.findViewById<ImageView>(R.id.image)
    val imageBtnFavorite = movieView.findViewById<ImageView>(R.id.favorite)

    fun bind(item: MovieItem){
        name.text = item.name
        image.setImageResource(item.image)
        imageBtnFavorite.setImageResource(item.imageFavorite)
    }
}