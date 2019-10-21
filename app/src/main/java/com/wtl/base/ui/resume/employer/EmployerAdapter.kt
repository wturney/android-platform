package com.wtl.base.ui.resume.employer

import android.view.ViewGroup
import com.wtl.base.ui.util.RecyclerViewItemAdapter

class EmployerAdapter : RecyclerViewItemAdapter<EmployerItem, EmployerItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployerItemViewHolder =
        EmployerItemViewHolder(parent)
}
