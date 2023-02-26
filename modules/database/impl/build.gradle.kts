plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "${Libs.Project.applicationId}.database_impl"

    compileSdk = Libs.Project.compileSdk

    defaultConfig {
        minSdk = Libs.Project.minSdk
        targetSdk = Libs.Project.targetSdk

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.room:room-runtime:${Libs.Deps.roomVersion}")
    implementation("androidx.room:room-ktx:${Libs.Deps.roomVersion}")
    kapt("androidx.room:room-compiler:${Libs.Deps.roomVersion}")

    implementation("com.google.dagger:dagger:${Libs.Deps.daggerVersion}")
    kapt("com.google.dagger:dagger-compiler:${Libs.Deps.daggerVersion}")

    api(project(":modules:database:api"))
}
