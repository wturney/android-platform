object BuildProperties {
    object AppVersion {
        const val major = 0
        const val minor = 0
        const val patch = 1
        const val name = "$major.$minor.$patch"
        const val versionCode = (major * 1000000) + (minor * 10000) + (patch * 100)
    }

    object Android {
        const val applicationId = "com.wtl.base"
        const val minSdkVersion = 23
        const val compileSdkVersion = 28
        const val targetSdkVersion = 28
        const val buildToolsVersion = "29.0.2"
    }

    object Logging {
        const val tag = "\"BASE\""
    }
}
