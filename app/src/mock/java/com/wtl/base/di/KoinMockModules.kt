package com.wtl.base.di

import android.content.Context
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.wtl.base.BuildConfig
import com.wtl.base.api.MockFailureType
import com.wtl.base.api.MockResumeService
import com.wtl.base.api.service.ResumeService
import com.wtl.debug.ui.ContentViewWrapper
import com.wtl.debug.ui.common.DebugContentViewWrapper
import com.wtl.debug.ui.common.component.KeyValueDebugComponentFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import java.util.concurrent.TimeUnit

val mockPreferencesModule: Module = module(override = true) {
    single(named("mockRxPrefs")) {
        val prefs = androidApplication().getSharedPreferences("mock", Context.MODE_PRIVATE)
        RxSharedPreferences.create(prefs)
    }
}

val mockApiModule: Module = module {
    single {
        val prefs: RxSharedPreferences = get(named("mockRxPrefs"))
        NetworkBehavior.create().apply {
            setDelay(prefs.getLong("latency", 500).get(), TimeUnit.MILLISECONDS)
            setVariancePercent(prefs.getInteger("variance", 0).get())
            setErrorPercent(prefs.getInteger("errorPercent", 0).get())
            setFailurePercent(prefs.getInteger("failurePercent", 0).get())
            setFailureException(prefs.getEnum("failureType", MockFailureType.NULL_POINTER, MockFailureType::class.java).get().exception)
            setErrorFactory {
                Response.error<Any>(
                    prefs.getInteger("errorCode", 400).get(),
                    "{}".toResponseBody("application/json".toMediaType())
                )
            }
        }
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://localhost")
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val mockRetrofit = MockRetrofit.Builder(retrofit)
            .networkBehavior(get())
            .build()
        val mockServiceDelegate = mockRetrofit.create<ResumeService>(ResumeService::class.java)
        MockResumeService(mockServiceDelegate) as ResumeService
    }
}

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
    mockPreferencesModule,
    mockApiModule,
    mockUiModule
)
