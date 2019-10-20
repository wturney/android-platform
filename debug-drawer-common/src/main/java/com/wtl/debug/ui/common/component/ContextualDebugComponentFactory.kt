package com.wtl.debug.ui.common.component

import android.content.Context
import android.content.ContextWrapper
import androidx.fragment.app.FragmentActivity

abstract class ContextualDebugComponentFactory :
    DebugComponentFactory {
    abstract fun isApplicable(context: Context): Boolean

    /**
     * Returns the first FragmentActivity present in a given context hierarchy
     */
    protected fun unwrapContext(context: Context): FragmentActivity? {
        var currentContext = context
        while (currentContext is ContextWrapper) {
            if (currentContext is FragmentActivity) {
                return currentContext
            }

            currentContext = currentContext.baseContext
        }

        return null
    }
}
