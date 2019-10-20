package com.wtl.base.di

import org.koin.core.module.Module

val stageUiModule: Module = module(override = true) {
    single {
        DebugContentViewWrapper() as ContentViewWrapper
    }
}

val flavorModules = listOf(
    mockUiModule
)

