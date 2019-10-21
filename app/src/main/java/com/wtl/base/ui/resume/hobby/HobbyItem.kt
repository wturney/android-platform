package com.wtl.base.ui.resume.hobby

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.wtl.base.ui.util.AdapterItem

data class HobbyItem(
    override val id: Long,
    val name: String,
    @ColorInt val iconBackgroundColor: Int,
    @DrawableRes val icon: Int
) : AdapterItem
