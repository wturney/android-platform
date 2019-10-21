package com.wtl.base.ui.resume.employer

import android.content.res.ColorStateList
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.wtl.base.R
import com.wtl.base.ui.util.BaseViewHolder
import kotlinx.android.synthetic.main.view_resume_employer_item.view.*

class EmployerItemViewHolder(parent: ViewGroup) : BaseViewHolder<EmployerItem>(
    parent, R.layout.view_resume_employer_item
) {
    override fun bind(item: EmployerItem) {
        itemView.employerNameView.text = item.name
        itemView.roleNameView.text = item.role

        ViewCompat.setBackgroundTintList(itemView.employerLogoView, ColorStateList.valueOf(item.logoBackgroundColor))
        Glide.with(itemView)
            .load(item.logo)
            .transition(withCrossFade())
            .into(itemView.employerLogoView)
    }
}
