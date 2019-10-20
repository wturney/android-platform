package com.wtl.debug.ui.common.component

import android.content.Context
import androidx.fragment.app.FragmentActivity

abstract class ActivityDebugComponentFactory<A : FragmentActivity>(
    private val activityClass: Class<A>
) : ContextualDebugComponentFactory() {
    override fun isApplicable(context: Context): Boolean {
        return unwrapContext(context)?.javaClass == activityClass
    }
}
