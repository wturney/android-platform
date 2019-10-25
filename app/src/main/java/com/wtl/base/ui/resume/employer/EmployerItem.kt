package com.wtl.base.ui.resume.employer

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.wtl.base.R
import com.wtl.base.api.model.Employer
import com.wtl.base.ui.util.recycler.adapter.Identifiable

data class EmployerItem(val employer: Employer) : Identifiable {
    override val id = employer.id

    @ColorRes
    val logoBackgroundColor: Int = when (id) {
        1L -> R.color.employerTred
        2L -> R.color.employerRei
        else -> R.color.employerOther
    }

    @DrawableRes
    val logo: Int = when (id) {
        1L -> R.drawable.ic_tred_160dp
        2L -> R.drawable.ic_rei_160dp
        else -> R.drawable.ic_employer_120dp
    }
}
