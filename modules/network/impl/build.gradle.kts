plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "${Libs.Project.applicationId}.network_impl"

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

    testImplementation("junit:junit:${Libs.TestDeps.junitVersion}")
    androidTestImplementation("androidx.test.ext:junit:${Libs.TestDeps.junitExtVersion}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Libs.TestDeps.espresspVersion}")

    implementation("com.google.dagger:dagger:${Libs.Deps.daggerVersion}")
    kapt("com.google.dagger:dagger-compiler:${Libs.Deps.daggerVersion}")

    implementation("com.squareup.okhttp3:okhttp:${Libs.Deps.okHttpVersion}")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:${Libs.Deps.okHttpVersion}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Libs.Deps.okHttpVersion}")

    implementation("com.squareup.retrofit2:retrofit:${Libs.Deps.retrofitVersion}")
    implementation("com.squareup.retrofit2:converter-moshi:${Libs.Deps.retrofitVersion}")

    implementation("com.squareup.moshi:moshi-kotlin:${Libs.Deps.moshiVersion}")
    implementation("com.squareup.moshi:moshi-adapters:${Libs.Deps.moshiVersion}")

    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    api(project(":modules:network:api"))
    api(project(":modules:core:api"))
}
