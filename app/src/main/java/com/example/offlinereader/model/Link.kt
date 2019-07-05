package com.example.offlinereader.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Link(
    val url: String,
    var isDownloaded: Boolean = false
) : Parcelable