package com.wtl.debug.ui.common.component

import android.content.Context
import android.view.ViewGroup

interface DebugComponentFactory {
    fun createComponent(context: Context, parent: ViewGroup): DebugComponent
}

