plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = libs.versions.applicationId.get()

    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = libs.versions.applicationId.get()
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures.apply {
        dataBinding = true
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
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.junit.ext)
    androidTestImplementation(libs.test.junit.ktx)
    androidTestImplementation(libs.test.kaspresso)

    implementation(libs.android.coreKtx)
    implementation(libs.android.appcompat)

    implementation(libs.dagger)
    kapt(libs.dagger.kapt)

    implementation(libs.cicerone)
    implementation(libs.timber)

    implementation(project(":modules:core:factory"))

    implementation(project(":modules:feature:main"))
    implementation(project(":modules:feature:search"))
    implementation(project(":modules:feature:favorites"))
    implementation(project(":modules:feature:upload"))
    implementation(project(":modules:feature:login"))
    implementation(project(":modules:base"))
}
