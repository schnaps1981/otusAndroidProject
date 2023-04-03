plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = Libs.Project.applicationId

    compileSdk = Libs.Project.compileSdk

    defaultConfig {
        applicationId = Libs.Project.applicationId
        minSdk = Libs.Project.minSdk
        targetSdk = Libs.Project.targetSdk
        versionCode = Libs.Project.versionCode
        versionName = Libs.Project.versionName

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:${Libs.Deps.coreKtxVersion}")
    implementation("androidx.appcompat:appcompat:${Libs.Deps.appCompatVersion}")

    implementation("com.google.dagger:dagger:${Libs.Deps.daggerVersion}")
    kapt("com.google.dagger:dagger-compiler:${Libs.Deps.daggerVersion}")

    implementation("com.github.terrakok:cicerone:${Libs.Deps.ciceroneVersion}")

    implementation("com.jakewharton.timber:timber:5.0.1")

    implementation(project(":modules:core:factory"))

    implementation(project(":modules:feature:main"))
    implementation(project(":modules:feature:search"))
    implementation(project(":modules:feature:favorites"))
    implementation(project(":modules:feature:upload"))
    implementation(project(":modules:feature:login"))
    implementation(project(":modules:base"))
}
