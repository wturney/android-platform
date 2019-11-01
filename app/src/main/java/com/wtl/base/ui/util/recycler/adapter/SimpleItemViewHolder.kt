package com.wtl.base.ui.util.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.wtl.base.ui.resume.employer.EmployerItem

/**
 * Convenience ViewHolder base class that will inflate the provided layout ID using the parent context.
 */
abstract class SimpleItemViewHolder<T : Any>(
    parent: ViewGroup,
    @LayoutRes private val layoutId: Int
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {

    protected lateinit var item: T

    @CallSuper
    open fun bind(item: T) {
        this.item = item
    }
}
