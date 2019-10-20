package com.wtl.debug.ui.common

import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.wtl.debug.common.R
import com.wtl.debug.ui.ContentViewWrapper
import com.wtl.debug.ui.common.component.DebugComponentFactory

/**
 * Wraps the activity root layout with a context-aware debug drawer
 */
class DebugContentViewWrapper(private vararg val componentFactories: DebugComponentFactory) : ContentViewWrapper {
    override fun wrap(activity: FragmentActivity): ViewGroup {
        activity.setContentView(R.layout.dbg_drawer)
        val drawerContentView: ViewGroup = activity.findViewById(R.id.debugDrawerContentLayout)
        val drawerLayout = DebugDrawerLayout(activity, componentFactories.toList())
        drawerContentView.addView(drawerLayout)
        return activity.findViewById(R.id.debugContentLayout)
    }
}

