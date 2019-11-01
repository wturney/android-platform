package com.wtl.base

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.jakewharton.threetenabp.AndroidThreeTen
import com.wtl.base.pref.SettingPreferences
import com.wtl.base.di.buildTypeModules
import com.wtl.base.di.defaultModules
import com.wtl.base.di.flavorModules
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class AppNameApplication : Application() {

    private val loggingTree: Timber.Tree by inject()
    private val settings: SettingPreferences by inject()

    override fun onCreate() {
        super.onCreate()
        initDateTime()

        startKoin {
            androidContext(this@AppNameApplication)
            if (BuildConfig.DEBUG) {
                androidLogger()
            }
            modules(defaultModules + buildTypeModules + flavorModules)
        }

        initLogging()
        initTheme()
    }

    private fun initDateTime() {
        AndroidThreeTen.init(this)
    }

    private fun initLogging() {
        Timber.plant(loggingTree)
        Timber.tag(BuildConfig.LOG_TAG)
    }

    private fun initTheme() {
        val nightMode =
            AppCompatDelegate.setDefaultNightMode(
                when (settings.nightMode.get()) {
                    true -> AppCompatDelegate.MODE_NIGHT_YES
                    false -> AppCompatDelegate.MODE_NIGHT_NO
                }
            )
    }
}
