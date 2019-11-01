package com.wtl.base.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Employer(
    val id: Long,
    val name: String,
    val shortName: String?,
    val roles: List<Role> = emptyList()
) : Parcelable

