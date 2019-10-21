package com.wtl.base.ui.resume.employer

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.wtl.base.ui.util.AdapterItem

data class EmployerItem(
    override val id: Long,
    val name: String,
    val role: String,
    @ColorInt val logoBackgroundColor: Int,
    @DrawableRes val logo: Int
) : AdapterItem
