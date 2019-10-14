object Dependencies {

    object Plugins {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    }

    const val kotlinJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    object Support {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.androidSupport}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.androidSupport}"
    }

    object Arch {

        object Lifecycle {
            val runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.archLifecycle}"
            val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.archLifecycle}"
            val liveData = "androidx.lifecycle:lifecycle-livedata:${Versions.archLifecycle}"
            val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.archLifecycle}"
            val viewModelKotlinExts = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.archLifecycle}"
        }

    }

    object Test {

        val junit = "junit:junit:${Versions.junit}"
        val androidTestRunner = "com.android.support.test:runner:${Versions.androidTestRunner}"
        val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"

    }

//    object RxJava2 {
//        val core = "io.reactivex.rxjava2:rxjava:${Versions.rxJava2}"
//        val android = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
//        val kotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}
//    }

//    val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTenAbp}"
//
//    object OkHttp3 {
//        val loggingIntercepter = "com.squareup.okhttp3:logging-interceptor:3.9.1"
//    }
//
//    object Retrofit {
//        val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
//        val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
//        val adapterRxJava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
//    }

}
