package com.wtl.base.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hobby(
    val id: Long,
    val name: String
) : Parcelable
