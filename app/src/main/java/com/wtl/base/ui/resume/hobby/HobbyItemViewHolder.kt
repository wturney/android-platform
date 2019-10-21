package com.wtl.base.ui.resume.hobby

import android.content.res.ColorStateList
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.wtl.base.R
import com.wtl.base.ui.util.BaseViewHolder
import kotlinx.android.synthetic.main.view_resume_hobby_item.view.*

class HobbyItemViewHolder(parent: ViewGroup) : BaseViewHolder<HobbyItem>(
    parent, R.layout.view_resume_hobby_item
) {
    override fun bind(item: HobbyItem) {
        itemView.hobbyNameView.text = item.name

        ViewCompat.setBackgroundTintList(itemView.hobbyIconView, ColorStateList.valueOf(item.iconBackgroundColor))
        Glide.with(itemView)
            .load(item.icon)
            .transition(withCrossFade())
            .into(itemView.hobbyIconView)
    }
}
