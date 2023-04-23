plugins {
    alias (libs.plugins.android.library)
    alias (libs.plugins.kotlin.android)
    alias (libs.plugins.kotlin.kapt)
    alias(libs.plugins.test.kotlin.allopen)
}

configure<org.jetbrains.kotlin.allopen.gradle.AllOpenExtension> {
    annotation("com.imgur.core_api.viewmodel.TestOpen")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    namespace = "${libs.versions.applicationId.get()}.core_api"

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.android.appcompat)
    implementation(libs.android.coreKtx)

    implementation(libs.dagger)
    kapt(libs.dagger.kapt)

    implementation(libs.cicerone)

    implementation(project(":modules:base"))
}
