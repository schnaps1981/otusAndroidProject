buildscript {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Libs.kotlinVersion}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
   }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
