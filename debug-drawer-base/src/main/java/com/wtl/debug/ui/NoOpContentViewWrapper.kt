package com.wtl.debug.ui

import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity

/**
 * No-Op wrapper that just returns the unmodified root layout.
 */
class NoOpContentViewWrapper : ContentViewWrapper {
    override fun wrap(activity: FragmentActivity): ViewGroup =
        activity.findViewById(android.R.id.content)
}
