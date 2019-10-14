buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Dependencies.Plugins.gradle)
        classpath(kotlin("gradle-plugin", version = Versions.kotlin))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
