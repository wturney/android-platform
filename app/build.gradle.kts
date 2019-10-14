plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
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
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(Dependencies.kotlinJdk7)
    implementation(Dependencies.Support.appCompat)
    implementation(Dependencies.Support.constraintLayout)
    implementation(Dependencies.Support.coreKtx)
    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Test.androidTestRunner)
    androidTestImplementation(Dependencies.Test.espresso)
}
