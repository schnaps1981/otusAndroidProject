plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "${libs.versions.applicationId.get()}.upload"

    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
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

    implementation(libs.android.coreKtx)
    implementation(libs.android.appcompat)
    implementation(libs.android.material)
    implementation(libs.android.recycler)
    implementation(libs.android.swiperefresh)
    implementation(libs.android.fragment.ktx)
    implementation(libs.android.activity.ktx)

    implementation(libs.dagger)
    kapt(libs.dagger.kapt)

    implementation(libs.timber)
    implementation(libs.okhttp)

    implementation(project(":modules:core:api"))
    implementation(project(":modules:base"))
    implementation(project(":modules:network:factory"))
}
