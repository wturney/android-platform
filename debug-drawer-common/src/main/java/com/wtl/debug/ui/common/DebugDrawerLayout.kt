package com.wtl.debug.ui.common

import android.annotation.SuppressLint
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.wtl.debug.common.R
import com.wtl.debug.ui.common.component.ContextualDebugComponentFactory
import com.wtl.debug.ui.common.component.DebugComponent
import com.wtl.debug.ui.common.component.DebugComponentFactory

@SuppressLint("ViewConstructor")
class DebugDrawerLayout(
    activity: FragmentActivity,
    factories: Iterable<DebugComponentFactory>
) : LinearLayout(activity), LifecycleObserver, FragmentManager.OnBackStackChangedListener {

    private val permanentComponentFactories =
        factories.filter { it !is ContextualDebugComponentFactory }
    private val contextualComponentFactories: List<ContextualDebugComponentFactory> =
        factories.mapNotNull { it as? ContextualDebugComponentFactory }

    private val contextualComponents: MutableList<DebugComponent> = mutableListOf()
    private val permanentComponents: MutableList<DebugComponent> = mutableListOf()

    init {
        orientation = VERTICAL
        fitsSystemWindows = false
        activity.lifecycle.addObserver(this)
        activity.supportFragmentManager.addOnBackStackChangedListener(this)
        inflate(context, R.layout.dbg_header, this)
    }

    override fun onBackStackChanged() {
        clearComponents(false)
        createComponents(false)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onDestroyActivity(source: LifecycleOwner) {
        clearComponents(true)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onCreateActivity(source: LifecycleOwner) {
        createComponents(true)
    }

    private fun clearComponents(includePermanent: Boolean) {
        val components = when {
            includePermanent -> permanentComponents + contextualComponents
            else -> contextualComponents
        }

        components.onEach {
            it.onCleared()
            removeView(it.view)
        }

        contextualComponents.clear()
        if (includePermanent) {
            permanentComponents.clear()
        }
    }

    private fun createComponents(includePermanent: Boolean) {
        val factories = when {
            includePermanent -> permanentComponentFactories + contextualComponentFactories
            else -> contextualComponentFactories
        }

        factories
            .filter {
                when (it) {
                    is ContextualDebugComponentFactory -> it.isApplicable(context)
                    else -> true
                }
            }
            .forEach {
                val componentList = when (it) {
                    is ContextualDebugComponentFactory -> contextualComponents
                    else -> permanentComponents
                }
                val component = it.createComponent(context, this)
                val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                    .apply {
                        topMargin = context.resources.getDimensionPixelSize(R.dimen.spacing_medium)
                    }
                addView(component.view, params)
                componentList.add(component)
            }
    }
}
