package com.wtl.base.di

import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import timber.log.Timber

val debugLoggingModule: Module = module(override = true) {
    single { Timber.DebugTree() as Timber.Tree }

    single(named("loggingHttpInterceptor")) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }
    }
}

val buildTypeModules = listOf(
    debugLoggingModule
)
