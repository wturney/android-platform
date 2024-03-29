package com.wtl.debug.ui

import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity

interface ContentViewWrapper {
    /**
     * Provides an opportunity to wrap the root content view of a given activity.
     * E.g. wrapping the actual content with a debug DrawerLayout
     */
    fun wrap(activity: FragmentActivity): ViewGroup
}
