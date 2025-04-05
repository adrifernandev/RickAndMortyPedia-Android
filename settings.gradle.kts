pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "RickAndMortyPedia"
include(":app")
include(":designsystem")
include(":common:presentation")
include(":feature:home:presentation")
include(":feature:character:data")
include(":common:data")
include(":common:di")
include(":common:fundaments")
include(":feature:character:domain")
include(":feature:characterdetail:presentation")
include(":feature:character:presentation")
include(":feature:location:domain")
include(":feature:location:data")
