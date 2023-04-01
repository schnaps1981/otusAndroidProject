plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "${Libs.Project.applicationId}.search"
    compileSdk = Libs.Project.compileSdk

    defaultConfig {
        minSdk = Libs.Project.minSdk
        targetSdk = Libs.Project.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
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

    implementation("androidx.core:core-ktx:${Libs.Deps.coreKtxVersion}")
    implementation("androidx.appcompat:appcompat:${Libs.Deps.appCompatVersion}")
    implementation("com.google.android.material:material:${Libs.Deps.materialVersion}")

    testImplementation("junit:junit:${Libs.TestDeps.junitVersion}")
    androidTestImplementation("androidx.test.ext:junit:${Libs.TestDeps.junitExtVersion}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Libs.TestDeps.espresspVersion}")

    implementation("com.google.dagger:dagger:${Libs.Deps.daggerVersion}")
    kapt("com.google.dagger:dagger-compiler:${Libs.Deps.daggerVersion}")

    implementation("androidx.fragment:fragment-ktx:${Libs.Deps.fragmentKtxVersion}")

    implementation("com.jakewharton.timber:timber:5.0.1")

    implementation("androidx.recyclerview:recyclerview:1.3.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    implementation(project(":modules:core:api"))
    implementation(project(":modules:base_ui"))

    implementation(project(":modules:network:factory"))
}
