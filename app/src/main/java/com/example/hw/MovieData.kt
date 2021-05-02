package com.example.hw

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieData (val name: String, val details: String, val image: Int) : Parcelable{
}