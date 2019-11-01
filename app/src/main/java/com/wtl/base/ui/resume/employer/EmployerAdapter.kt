package com.wtl.base.ui.resume.employer

import android.view.ViewGroup
import com.wtl.base.ui.util.recycler.adapter.SimpleItemAdapter
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class EmployerAdapter : SimpleItemAdapter<EmployerItem, EmployerItemViewHolder>() {

    private val itemClickSubject = PublishSubject.create<EmployerItem>()

    fun itemClicks(): Observable<EmployerItem> = itemClickSubject.hide()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployerItemViewHolder =
        EmployerItemViewHolder(parent, itemClickSubject)
}
