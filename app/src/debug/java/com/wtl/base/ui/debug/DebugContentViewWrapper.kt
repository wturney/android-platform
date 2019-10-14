package com.wtl.base.ui.debug

import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.wtl.base.R
import com.wtl.base.ui.ContentViewWrapper

/**
 * Wraps the activity root layout with a context-aware debug drawer
 */
class DebugContentViewWrapper : ContentViewWrapper {
    override fun wrap(activity: FragmentActivity): ViewGroup {
        activity.setContentView(R.layout.debug_drawer)
        val drawerContentView: ViewGroup = activity.findViewById(R.id.debugDrawerContentLayout)
        val drawerLayout = DebugDrawerLayout(activity)
        drawerContentView.addView(drawerLayout)
        return activity.findViewById(R.id.debugContentLayout)
    }
}

