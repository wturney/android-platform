package com.wtl.base.di

import android.content.Context
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.squareup.moshi.Moshi
import com.wtl.base.pref.SettingPreferences
import com.wtl.base.repository.ResumeRepository
import com.wtl.base.repository.cache.ExpiringServiceCache
import com.wtl.base.repository.cache.ServiceCache
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

val settingsModule: Module = module {
    single {
        val prefs = androidApplication().getSharedPreferences("settings", Context.MODE_PRIVATE)
        SettingPreferences(RxSharedPreferences.create(prefs))
    }
}

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
    settingsModule,
    apiModule,
    viewModelModule
)
