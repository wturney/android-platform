package com.wtl.base.ui.resume.hobby

import android.view.ViewGroup
import com.wtl.base.ui.util.RecyclerViewItemAdapter

class HobbyAdapter : RecyclerViewItemAdapter<HobbyItem, HobbyItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbyItemViewHolder =
        HobbyItemViewHolder(parent)
}
