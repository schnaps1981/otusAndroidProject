pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "imgur"
include (":app")
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
