package com.wtl.base.ui.util.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Convenience ViewHolder base class that will inflate the provided layout ID using the parent context.
 */
abstract class SimpleItemViewHolder<T : Any>(parent: ViewGroup, @LayoutRes private val layoutId: Int) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {
    abstract fun bind(item: T)
}
