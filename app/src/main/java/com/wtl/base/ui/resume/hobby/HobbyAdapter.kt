package com.wtl.base.ui.resume.hobby

import android.view.ViewGroup
import com.wtl.base.ui.util.recycler.adapter.SimpleItemAdapter

class HobbyAdapter : SimpleItemAdapter<HobbyItem, HobbyItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbyItemViewHolder =
        HobbyItemViewHolder(parent)
}
