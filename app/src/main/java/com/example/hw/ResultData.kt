package com.example.hw

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ResultData (val checkBox: Boolean, val comment: String) : Parcelable {
}