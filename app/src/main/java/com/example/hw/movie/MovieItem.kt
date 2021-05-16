package com.example.hw.movie

data class MovieItem (val name: String, val details: String, val image: Int, var isVisited : Boolean = false, var isFavorite : Boolean = false, var  imageFavorite : Int)