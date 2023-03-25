plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "${Libs.Project.applicationId}.main"

    compileSdk = Libs.Project.compileSdk

    defaultConfig {
        minSdk = Libs.Project.minSdk
        targetSdk = Libs.Project.targetSdk

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

    implementation("androidx.core:core-ktx:${Libs.Deps.coreKtxVersion}")
    implementation("androidx.appcompat:appcompat:${Libs.Deps.appCompatVersion}")
    implementation("com.google.android.material:material:${Libs.Deps.materialVersion}")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Libs.Deps.lifecycleRuntimeVersion}")
    implementation("androidx.lifecycle:lifecycle-extensions:${Libs.Deps.lifecycleExtensionsVersion}")

    implementation("com.google.dagger:dagger:${Libs.Deps.daggerVersion}")
    kapt("com.google.dagger:dagger-compiler:${Libs.Deps.daggerVersion}")

    implementation("androidx.activity:activity-ktx:${Libs.Deps.activityKtxVersion}")

    implementation("com.github.terrakok:cicerone:${Libs.Deps.ciceroneVersion}")

    implementation(project(":modules:core:api"))
    implementation(project(":modules:base_ui"))
}
