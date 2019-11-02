package com.wtl.base.ui.resume.employer

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
import kotlinx.android.synthetic.main.view_resume_employer_item.view.*

class EmployerItemViewHolder(
    parent: ViewGroup,
    itemClickSubject: Subject<EmployerItem>
) : SimpleItemViewHolder<EmployerItem>(
    parent, R.layout.view_resume_employer_item
) {

    init {
        itemView.clicks()
            .map { item }
            .subscribe(itemClickSubject)
    }

    override fun bind(item: EmployerItem) {
        super.bind(item)

        itemView.employerNameView.text = item.employer.name
        itemView.roleNameView.text = item.employer.roles.first().name

        ViewCompat.setBackgroundTintList(
            itemView.employerLogoView,
            ColorStateList.valueOf(ContextCompat.getColor(itemView.context, item.logoBackgroundColor))
        )

        Glide.with(itemView)
            .load(item.logo)
            .transition(withCrossFade())
            .into(itemView.employerLogoView)
    }
}
