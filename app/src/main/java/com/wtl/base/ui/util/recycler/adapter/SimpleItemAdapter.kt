package com.wtl.base.ui.util.recycler.adapter

import androidx.recyclerview.widget.RecyclerView
import com.wtl.base.ui.util.recycler.diff
import kotlin.properties.Delegates

interface Identifiable {
    val id: Long
}

/**
 * Convenience RecyclerView adapter that handles common use cases for simple single-model lists with stable IDs
 */
abstract class SimpleItemAdapter<I : Identifiable, VH> : RecyclerView.Adapter<VH>() where VH : SimpleItemViewHolder<I> {
    var items: List<I> by Delegates.observable(emptyList()) { _, oldList, newList ->
        diff(oldList, newList)
    }

    init {
        this.setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = items[position].id
    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])

}
