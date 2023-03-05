object Libs {

    const val kotlinVersion = "1.7.20"

    object Project {
        const val compileSdk = 33
        const val buildToolsVersion = "33.0.1"
        const val applicationId = "com.imgur"
        const val minSdk = 26
        const val targetSdk = 33
        const val versionCode = 1
        const val versionName = "1.0.0"

        const val appName = "Imgur express"

        const val debugIteration = 2
    }

    object Deps {
        const val daggerVersion = "2.44.2"
        const val coreKtxVersion = "1.9.0"
        const val appCompatVersion = "1.6.1"
        const val materialVersion = "1.8.0"

        const val constrainLayoutVersion = "2.1.4"

        const val roomVersion = "2.5.0"

        const val lifecycleRuntimeVersion = "2.5.1"
        const val lifecycleExtensionsVersion = "2.2.0"
    }

    object TestDeps{
        const val junitVersion = "4.13.2"
        const val junitExtVersion = "1.1.5"
        const val espresspVersion = "3.5.1"
    }
}
