package com.wtl.debug.ui.common.component

import android.view.View

open class ViewDebugComponent(override val view: View) :
    DebugComponent {
    override fun onCleared() {}
}
