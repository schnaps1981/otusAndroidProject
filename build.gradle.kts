//plugins {
//    alias(libs.plugins.kotlin.allopen) apply(false)
//}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.test.kotlin.allopen) apply false
}
//buildscript {
//    repositories {
//        google()
//        gradlePluginPortal()
//        mavenCentral()
//    }

//    dependencies {
//        classpath("com.android.tools.build:gradle:7.3.1")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Libs.kotlinVersion}")
//        classpath("org.jetbrains.kotlin:kotlin-allopen:${Libs.kotlinVersion}")
//    }
//}

//allprojects {
//    repositories {
//        google()
//        mavenCentral()
//    }
//}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
