plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-allopen")
}

configure<org.jetbrains.kotlin.allopen.gradle.AllOpenExtension> {
    annotation("com.imgur.core_api.viewmodel.TestOpen")
}

android {
    namespace = "${Libs.Project.applicationId}.favorites"
    compileSdk = Libs.Project.compileSdk

    defaultConfig {
        minSdk = Libs.Project.minSdk
        targetSdk = Libs.Project.targetSdk

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    testOptions.unitTests.isIncludeAndroidResources = true
}

dependencies {
//    testImplementation("junit:junit:${Libs.TestDeps.junitVersion}")
//    androidTestImplementation("androidx.test.ext:junit:${Libs.TestDeps.junitExtVersion}")
//    androidTestImplementation("androidx.test.espresso:espresso-core:${Libs.TestDeps.espresspVersion}")
//    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
//    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
//    testImplementation("androidx.arch.core:core-testing:2.2.0")
//    testImplementation("androidx.test.ext:junit:1.1.5")
//    testImplementation("org.robolectric:robolectric:4.8.2")
//    debugImplementation("androidx.fragment:fragment-testing:1.5.6")
//    testImplementation("androidx.test.espresso:espresso-core:${Libs.TestDeps.espresspVersion}")


    debugImplementation("androidx.fragment:fragment-testing:1.4.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.test.ext:junit:1.1.3")
    testImplementation("androidx.test.espresso:espresso-core:3.4.0")
    testImplementation("androidx.test:core:1.4.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("org.robolectric:robolectric:4.8")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")




    implementation("androidx.core:core-ktx:${Libs.Deps.coreKtxVersion}")
    implementation("androidx.appcompat:appcompat:${Libs.Deps.appCompatVersion}")
    implementation("com.google.android.material:material:${Libs.Deps.materialVersion}")

    implementation("com.google.dagger:dagger:${Libs.Deps.daggerVersion}")
    kapt("com.google.dagger:dagger-compiler:${Libs.Deps.daggerVersion}")

    implementation("androidx.fragment:fragment-ktx:${Libs.Deps.fragmentKtxVersion}")

    implementation("androidx.recyclerview:recyclerview:1.3.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    implementation(project(":modules:core:api"))
    implementation(project(":modules:base"))
    implementation(project(":modules:database:factory"))
}
