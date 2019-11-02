package com.wtl.base.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.Duration
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate

@Parcelize
data class Role(
    val name: String,
    val description: List<String> = emptyList(),
    val technologies: List<String> = emptyList(),
    val dates: List<Pair<LocalDate, LocalDate?>> = emptyList()
) : Parcelable {
}

