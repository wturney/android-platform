package com.wtl.base.ui.resume.hobby

import android.content.res.ColorStateList
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.jakewharton.rxbinding3.view.clicks
import com.wtl.base.R
import com.wtl.base.ui.util.recycler.adapter.SimpleItemViewHolder
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.view_resume_hobby_item.view.*

class HobbyItemViewHolder(
    parent: ViewGroup,
    itemClickSubject: Subject<HobbyItem>
) : SimpleItemViewHolder<HobbyItem>(
    parent, R.layout.view_resume_hobby_item
) {

    init {
        itemView.clicks()
            .map { item }
            .subscribe(itemClickSubject)
    }

    override fun bind(item: HobbyItem) {
        super.bind(item)

        itemView.hobbyNameView.text = item.hobby.name

        ViewCompat.setBackgroundTintList(
            itemView.hobbyIconView,
            ColorStateList.valueOf(ContextCompat.getColor(itemView.context, item.iconBackgroundColor))
        )

        Glide.with(itemView)
            .load(item.icon)
            .transition(withCrossFade())
            .into(itemView.hobbyIconView)
    }
}
