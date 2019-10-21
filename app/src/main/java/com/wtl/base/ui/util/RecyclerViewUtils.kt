package com.wtl.base.ui.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

/**
 * Represents an adapter item that provides a standard Long identifier for use with RecyclerView adapters
 */
interface AdapterItem {
    val id: Long
}

/**
 * Represents a bindable adapter item for use with convenience classes
 */
interface AdapterItemViewHolder<I : AdapterItem> {
    fun bind(item: I)
}

/**
 * Convenience RecyclerView adapter that handles common use cases for simple single-model lists
 */
abstract class RecyclerViewItemAdapter<I : AdapterItem, VH> :
    RecyclerView.Adapter<VH>() where VH : AdapterItemViewHolder<I>, VH : RecyclerView.ViewHolder {
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

/**
 * Convenience ViewHolder implementation that will inflate the provided layout ID using the parent context
 */
abstract class BaseViewHolder<I : AdapterItem>(parent: ViewGroup, @LayoutRes private val layoutId: Int) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)),
    AdapterItemViewHolder<I>

/**
 * Convenience function for quickly dispatching diff updates on RecyclerView model objects that provide
 * an ID. This function uses the default equality comparison for the implementation of areContentsTheSame
 */
fun <T : AdapterItem> RecyclerView.Adapter<*>.diff(oldList: List<T>, newList: List<T>) {
    val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
    })
    diff.dispatchUpdatesTo(this)
}
