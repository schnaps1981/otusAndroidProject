pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.buildFileName = "build.gradle.kts"
rootProject.name = "imgur"

include(":app")
include(":modules:network")
include(":modules:network:api")
include(":modules:network:impl")
include(":modules:network:factory")
include(":modules:core")
include(":modules:core:api")
include(":modules:core:impl")
include(":modules:core:factory")
include(":modules:feature")
include(":modules:feature:main")
include(":modules:database")
include(":modules:database:api")
include(":modules:database:factory")
include(":modules:database:impl")
include(":modules:feature:search")
include(":modules:base")
include(":modules:feature:favorites")
include(":modules:feature:upload")
include(":modules:feature:login")
