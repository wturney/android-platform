package com.wtl.base.ui.debug

import android.annotation.SuppressLint
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

@SuppressLint("ViewConstructor")
class DebugDrawerLayout(
    activity: FragmentActivity
) : LinearLayout(activity), LifecycleObserver, FragmentManager.OnBackStackChangedListener {
    override fun onBackStackChanged() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onDestroyActivity(source: LifecycleOwner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onCreateActivity(source: LifecycleOwner) {
    }
}
