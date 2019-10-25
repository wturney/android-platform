package com.wtl.base.ui.resume.hobby

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.wtl.base.R
import com.wtl.base.api.model.Hobby
import com.wtl.base.ui.util.recycler.adapter.Identifiable

data class HobbyItem(val hobby: Hobby) : Identifiable {
    override val id = hobby.id

    @ColorRes
    val iconBackgroundColor: Int = when (id) {
        1L -> R.color.hobbyCycling
        2L -> R.color.hobbySnow
        3L -> R.color.hobbyBass
        4L -> R.color.hobbyDota
        else -> R.color.hobbyOther
    }

    @DrawableRes
    val icon: Int = when (id) {
        1L -> R.drawable.ic_bike_60dp
        2L -> R.drawable.ic_snow_60dp
        3L -> R.drawable.ic_music_note_60dp
        4L -> R.drawable.ic_dota2_60dp
        else -> R.drawable.ic_hobby_24dp
    }
}
