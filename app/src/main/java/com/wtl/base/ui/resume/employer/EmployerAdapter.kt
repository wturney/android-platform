package com.wtl.base.ui.resume.employer

import android.view.ViewGroup
import com.wtl.base.ui.util.recycler.adapter.SimpleItemAdapter

class EmployerAdapter : SimpleItemAdapter<EmployerItem, EmployerItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployerItemViewHolder =
        EmployerItemViewHolder(parent)
}
