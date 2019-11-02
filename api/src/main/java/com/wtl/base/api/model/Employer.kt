package com.wtl.base.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDate

@Parcelize
data class Employer(
    val id: Long,
    val name: String,
    val shortName: String?,
    val roles: List<Role> = emptyList(),
    val url: String,
    val appUrls: Map<AppPlatform, String> = emptyMap()
) : Parcelable