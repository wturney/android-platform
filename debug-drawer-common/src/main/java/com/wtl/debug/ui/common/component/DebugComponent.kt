package com.wtl.debug.ui.common.component

import android.view.View

interface DebugComponent {
    val view: View
    fun onCleared()
}
