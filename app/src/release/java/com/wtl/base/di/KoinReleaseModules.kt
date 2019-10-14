package com.wtl.base.di

import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import timber.log.Timber

val releaseLoggingModule: Module = module {
    single {
        object : Timber.Tree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {}
        } as Timber.Tree
    }

    single(named("loggingHttpInterceptor")) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.NONE
        }
    }
}

val buildTypeModules = listOf(
    releaseLoggingModule
)
