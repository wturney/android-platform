plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(BuildProperties.Android.compileSdkVersion)
    buildToolsVersion(BuildProperties.Android.buildToolsVersion)

    defaultConfig {
        minSdkVersion(BuildProperties.Android.minSdkVersion)
        targetSdkVersion(BuildProperties.Android.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":debug-drawer-base"))

    // Platform
    implementation(Dependencies.kotlinJdk7)

    // Support
    implementation(Dependencies.Support.appCompat)
    implementation(Dependencies.Support.constraintLayout)
}
