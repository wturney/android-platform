plugins {
    id("java-library")
    id("kotlin")
}

dependencies {
    // Platform
    implementation(Dependencies.kotlinJdk7)

    // Support
    implementation(Dependencies.Retrofit.core)
    implementation(Dependencies.Retrofit.adapterRxJava2)
}
