package com.example.hw.movie

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.hw.R

class MovieHeaderVH(headerView: View) : RecyclerView.ViewHolder(headerView) {
    val inviteBtn : View = headerView.findViewById(R.id.invite)
    val favoriteBtn : View = headerView.findViewById(R.id.favorite)
}