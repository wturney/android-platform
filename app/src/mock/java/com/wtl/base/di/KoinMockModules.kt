package com.wtl.base.di

import com.wtl.base.BuildConfig
import com.wtl.debug.ui.ContentViewWrapper
import com.wtl.debug.ui.common.DebugContentViewWrapper
import com.wtl.debug.ui.common.component.KeyValueDebugComponentFactory
import org.koin.core.module.Module
import org.koin.dsl.module

val mockUiModule: Module = module(override = true) {
    single {
        DebugContentViewWrapper(
            KeyValueDebugComponentFactory(
                "Build Type" to BuildConfig.BUILD_TYPE,
                "Environment" to BuildConfig.FLAVOR
            )
        ) as ContentViewWrapper
    }
}

val flavorModules = listOf(
    mockUiModule
)
