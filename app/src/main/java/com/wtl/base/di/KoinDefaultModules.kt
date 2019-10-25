package com.wtl.base.di

import com.squareup.moshi.Moshi
import com.wtl.base.repository.ResumeRepository
import com.wtl.base.repository.cache.ExpiringServiceCache
import com.wtl.base.repository.cache.ServiceCache
import org.koin.core.module.Module
import org.koin.dsl.module


val apiModule: Module = module {
    single {
        Moshi.Builder()
            .build()
    }

    single {
        ExpiringServiceCache() as ServiceCache
    }

    single {
        ResumeRepository(get(), get())
    }
}

val defaultModules = listOf(
    apiModule,
    viewModelModule
)
