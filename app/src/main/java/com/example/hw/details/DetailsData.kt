package com.example.hw.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsData (val name: String, val details: String, val image: Int) : Parcelable {
}