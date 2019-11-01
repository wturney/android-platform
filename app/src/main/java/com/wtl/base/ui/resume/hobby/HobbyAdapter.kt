package com.wtl.base.ui.resume.hobby

import android.view.ViewGroup
import com.wtl.base.ui.util.recycler.adapter.SimpleItemAdapter
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class HobbyAdapter : SimpleItemAdapter<HobbyItem, HobbyItemViewHolder>() {

    private val itemClickSubject = PublishSubject.create<HobbyItem>()

    fun itemClicks(): Observable<HobbyItem> = itemClickSubject.hide()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbyItemViewHolder =
        HobbyItemViewHolder(parent, itemClickSubject)
}
