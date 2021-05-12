package com.example.hw.favorite

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hw.R
import com.example.hw.movie.MovieItem

class FavoriteVH (favoriteView: View) : RecyclerView.ViewHolder(favoriteView) {
    val name = favoriteView.findViewById<TextView>(R.id.name)
    val image = favoriteView.findViewById<ImageView>(R.id.image)

    fun bind(item: MovieItem){
        name.text = item.name
        image.setImageResource(item.image)
    }

}