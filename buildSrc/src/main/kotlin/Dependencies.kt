object Dependencies {

    object Plugins {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    }

    object Glide {
        const val core = "com.github.bumptech.glide:glide:${Versions.glide}"
    }

    const val kotlinJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"

    object Support {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.androidSupport}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.androidSupport}"
    }

    object Koin {
        const val android = "org.koin:koin-android:${Versions.koin}"
        const val scope = "org.koin:koin-androidx-scope:${Versions.koin}"
        const val viewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    }

    object Arch {

        object Lifecycle {
            const val runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.archLifecycle}"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.archLifecycle}"
            const val liveData = "androidx.lifecycle:lifecycle-livedata:${Versions.archLifecycle}"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.archLifecycle}"
            const val viewModelExts = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.archLifecycle}"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.archLifecycle}"
        }

    }

    object Test {

        const val junit = "junit:junit:${Versions.junit}"
        const val androidTestRunner = "com.android.support.test:runner:${Versions.androidTestRunner}"
        const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
        const val koin = "org.koin:koin-test:${Versions.koin}"

    }

    object Rx {
        const val core = "io.reactivex.rxjava2:rxjava:${Versions.Rx.java}"
        const val android = "io.reactivex.rxjava2:rxandroid:${Versions.Rx.android}"
        const val kotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.Rx.kotlin}"
    }

    const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTenAbp}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    object OkHttp {
        const val core = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val adapterRxJava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    }

}
