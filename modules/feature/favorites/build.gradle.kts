plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.test.kotlin.allopen)
}

configure<org.jetbrains.kotlin.allopen.gradle.AllOpenExtension> {
    annotation("com.imgur.core_api.viewmodel.TestOpen")
}

android {
    namespace = "${libs.versions.applicationId.get()}.favorites"
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

    testOptions.unitTests.isIncludeAndroidResources = true
}

dependencies {

    debugImplementation(libs.test.androidx.fragment)
    testImplementation(libs.test.junit)
    testImplementation(libs.test.junit.ext)
    testImplementation(libs.test.espresso.core)
    testImplementation(libs.test.androidx)
    testImplementation(libs.test.mockito.kotlin)
    testImplementation(libs.test.robolectric)
    testImplementation(libs.test.androidx.coreTesting)
    testImplementation(libs.test.kotlin.coroutines)

    implementation(libs.android.coreKtx)
    implementation(libs.android.appcompat)
    implementation(libs.android.material)
    implementation(libs.android.fragment.ktx)
    implementation(libs.android.recycler)
    implementation(libs.android.swiperefresh)

    implementation(libs.dagger)
    kapt(libs.dagger.kapt)

    implementation(project(":modules:core:api"))
    implementation(project(":modules:base"))
    implementation(project(":modules:database:factory"))
}
