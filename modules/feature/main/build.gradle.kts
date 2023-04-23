plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "${libs.versions.applicationId.get()}.main"

    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures.apply {
        dataBinding = true
        viewBinding = true
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
    implementation(libs.android.coreKtx)
    implementation(libs.android.appcompat)
    implementation(libs.android.material)
    implementation(libs.android.lifecycle.ktx)
    implementation(libs.android.lifecycle.ext)
    implementation(libs.android.activity.ktx)

    implementation(libs.dagger)
    kapt(libs.dagger.kapt)

    implementation(libs.cicerone)

    implementation(project(":modules:core:api"))
    implementation(project(":modules:base"))
}
