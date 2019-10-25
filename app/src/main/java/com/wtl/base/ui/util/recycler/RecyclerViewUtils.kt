package com.wtl.base.ui.util.recycler

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wtl.base.ui.util.recycler.adapter.Identifiable

/**
 * Convenience function for quickly dispatching diff updates on RecyclerView model objects that provide
 * an ID. This function uses the default equality comparison for the implementation of areContentsTheSame
 */
fun <T : Identifiable> RecyclerView.Adapter<*>.diff(oldList: List<T>, newList: List<T>) {
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
