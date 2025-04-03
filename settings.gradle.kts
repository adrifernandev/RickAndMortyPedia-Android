import com.adrifernandev.rickandmortypedia.buildsrc.Modules.APP
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.COMMON_PRESENTATION
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.DESIGN_SYSTEM
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.FEATURE_HOME_DATA
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.FEATURE_HOME_PRESENTATION

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
include(APP)
include(DESIGN_SYSTEM)
include(COMMON_PRESENTATION)
include(FEATURE_HOME_PRESENTATION)
include(FEATURE_HOME_DATA)
