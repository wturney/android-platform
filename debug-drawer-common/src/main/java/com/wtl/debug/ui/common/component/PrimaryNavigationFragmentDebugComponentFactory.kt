package com.wtl.debug.ui.common.component

import android.content.Context
import androidx.fragment.app.Fragment

abstract class PrimaryNavigationFragmentDebugComponentFactory<F : Fragment>(
    private val fragmentClass: Class<F>
) : ContextualDebugComponentFactory() {
    override fun isApplicable(context: Context): Boolean {
        val activity = unwrapContext(context)
        val navigationFragment = activity?.supportFragmentManager?.primaryNavigationFragment
        return navigationFragment?.javaClass == fragmentClass
    }
}
