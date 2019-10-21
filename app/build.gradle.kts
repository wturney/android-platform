import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

val debugKeystoreProperties = Properties().apply {
    load(rootProject.file("app/deploy/debug/keystore.properties").inputStream())
}

val releaseKeystoreProperties = Properties().apply {
    load(
        rootProject.file(
            System.getenv("KEYSTORE_PATH") ?: "app/deploy/debug/keystore.properties"
        ).inputStream()
    )
}

android {
    compileSdkVersion(BuildProperties.Android.compileSdkVersion)
    buildToolsVersion(BuildProperties.Android.buildToolsVersion)

    defaultConfig {
        applicationId = BuildProperties.Android.applicationId
        minSdkVersion(BuildProperties.Android.minSdkVersion)
        targetSdkVersion(BuildProperties.Android.targetSdkVersion)
        versionCode = BuildProperties.AppVersion.versionCode
        versionName = BuildProperties.AppVersion.name
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "LOG_TAG", BuildProperties.Logging.tag)
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = debugKeystoreProperties["keyAlias"] as String
            keyPassword = debugKeystoreProperties["keyPassword"] as String
            storeFile = rootProject.file(debugKeystoreProperties["storeFile"] as String)
            storePassword = debugKeystoreProperties["storePassword"] as String
        }
        create("release") {
            keyAlias = releaseKeystoreProperties["keyAlias"] as String
            keyPassword = releaseKeystoreProperties["keyPassword"] as String
            storeFile = rootProject.file(releaseKeystoreProperties["storeFile"] as String)
            storePassword = releaseKeystoreProperties["storePassword"] as String
        }
    }

    buildTypes {
        getByName("debug") {
            isTestCoverageEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions("env")
    productFlavors {
        create("mock") {
            setDimension("env")
            applicationIdSuffix = ".mock"
            resConfigs("en", "xxhdpi")
        }

        create("stage") {
            setDimension("env")
            applicationIdSuffix = ".stage"
            resConfigs("en", "xxhdpi")
        }

        create("prod") {
            setDimension("env")
        }
    }

    // Disables flavor / build type variants that should not exist. E.g. mockRelease, stageRelease, prodDebug
    variantFilter {
        val flavorNames = flavors.map { it.name }
        val prod = flavorNames.contains("prod")
        val nonProdReleaseVariant = !prod && buildType.name.contains("release")
        val prodDebugVariant = prod && buildType.name.contains("debug")
        if (nonProdReleaseVariant || prodDebugVariant) {
            setIgnore(true)
        }
    }
}

dependencies {
    // Platform
    implementation(Dependencies.kotlinJdk7)

    // Debug Drawer
    implementation(project(":debug-drawer-base"))
    debugImplementation(project(":debug-drawer-common"))

    // Material Design
    implementation(Dependencies.materialDesign)

    // Support
    implementation(Dependencies.Support.appCompat)
    implementation(Dependencies.Support.constraintLayout)
    implementation(Dependencies.Support.coreKtx)

    // Arch
    implementation(Dependencies.Arch.Lifecycle.runtime)
    implementation(Dependencies.Arch.Lifecycle.liveData)
    implementation(Dependencies.Arch.Lifecycle.extensions)
    implementation(Dependencies.Arch.Lifecycle.viewModel)
    implementation(Dependencies.Arch.Lifecycle.viewModelExts)
    kapt(Dependencies.Arch.Lifecycle.compiler)

    // Dependency Injection
    implementation(Dependencies.Koin.android)
    implementation(Dependencies.Koin.scope)
    implementation(Dependencies.Koin.viewModel)

    // RX
    implementation(Dependencies.Rx.core)
    implementation(Dependencies.Rx.kotlin)
    implementation(Dependencies.Rx.android)

    // Date / Time
    implementation(Dependencies.threeTenAbp)

    // Network
    implementation(Dependencies.OkHttp.core)
    implementation(Dependencies.OkHttp.logging)
    implementation(Dependencies.Retrofit.core)
    implementation(Dependencies.Retrofit.converterMoshi)
    implementation(Dependencies.Retrofit.adapterRxJava2)

    // Logging
    implementation(Dependencies.timber)

    // Images
    implementation(Dependencies.Glide.core)

    // Unit Tests
    testImplementation(Dependencies.Test.junit)

    // UI Tests
    androidTestImplementation(Dependencies.Test.androidTestRunner)
    androidTestImplementation(Dependencies.Test.espresso)
    androidTestImplementation(Dependencies.Test.koin)
}
