import com.adrifernandev.rickandmortypedia.buildsrc.Modules.COMMON_DATA
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.COMMON_DI
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.COMMON_FUNDAMENTS
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.COMMON_PRESENTATION
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.DESIGN_SYSTEM
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.FEATURE_CHARACTER_DATA
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.FEATURE_CHARACTER_DETAIL_PRESENTATION
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.FEATURE_CHARACTER_DOMAIN
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.FEATURE_CHARACTER_PRESENTATION
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.FEATURE_HOME_PRESENTATION
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.FEATURE_LOCATION_DATA
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.FEATURE_LOCATION_DOMAIN
import com.adrifernandev.rickandmortypedia.buildsrc.Versions

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
}

kover {
    reports {
        filters {
            excludes {
                annotatedBy("*Composable")
                classes("*.ui.*")
                classes("*.di.*")
                classes("*Activity")
                classes("*generated*")
                classes("*ComposableSingletons*")
            }
        }
        variant("debug") {
            xml {
                onCheck = true
            }
            html {
                onCheck = true
            }
        }
    }
}

android {
    namespace = Versions.APPLICATION_ID
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        applicationId = Versions.APPLICATION_ID
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.COMPILE_SDK
        versionCode = Versions.APP_VERSION_CODE
        versionName = Versions.APP_VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        getByName("debug")
        create("release") {
            storeFile = file("signing/rickAndMortyPediaKeystore")
            storePassword = System.getenv("RAMP_ANDROID_KEYSTORE_PASSWORD")
            keyAlias = System.getenv("RAMP_ANDROID_KEYSTORE_ALIAS")
            keyPassword = System.getenv("RAMP_ANDROID_KEYSTORE_PRIVATE_KEY_PASSWORD")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }
    compileOptions {
        sourceCompatibility = Versions.javaVersion
        targetCompatibility = Versions.javaVersion
    }
    kotlinOptions {
        jvmTarget = Versions.JVM_TARGET
    }
    buildFeatures {
        compose = true
    }
    lint {
        sarifReport = true
        checkDependencies = true
    }
}

dependencies {
    implementation(libs.bundles.layer.presentation)
    implementation(platform(libs.androidx.compose.bom))
    ksp(libs.bundles.compilers.ksp.generic)

    testImplementation(libs.bundles.testing.unit)

    // [IMPLEMENT ALL MODULES] //
    /* This is needed because of
    upload-sarif.yml, if not, it won't
    report about that module missing here
    to be fixed on #13 */
    implementation(project(DESIGN_SYSTEM))
    implementation(project(COMMON_PRESENTATION))
    implementation(project(FEATURE_HOME_PRESENTATION))
    implementation(project(FEATURE_CHARACTER_DOMAIN))
    implementation(project(FEATURE_CHARACTER_DATA))
    implementation(project(COMMON_DATA))
    implementation(project(COMMON_FUNDAMENTS))
    implementation(project(FEATURE_CHARACTER_DETAIL_PRESENTATION))
    implementation(project(FEATURE_CHARACTER_PRESENTATION))
    implementation(project(FEATURE_LOCATION_DOMAIN))
    implementation(project(FEATURE_LOCATION_DATA))

    // [DI Module implementation needed] //
    implementation(project(COMMON_DI))

    // [Kover Implementations] //
    kover(project(DESIGN_SYSTEM))
    kover(project(COMMON_PRESENTATION))
    kover(project(FEATURE_HOME_PRESENTATION))
    kover(project(FEATURE_CHARACTER_DATA))
    kover(project(COMMON_DATA))
    kover(project(COMMON_DI))
    kover(project(COMMON_FUNDAMENTS))
    kover(project(FEATURE_CHARACTER_DETAIL_PRESENTATION))
    kover(project(FEATURE_CHARACTER_DOMAIN))
    kover(project(FEATURE_CHARACTER_PRESENTATION))
    kover(project(FEATURE_LOCATION_DOMAIN))
    kover(project(FEATURE_LOCATION_DATA))
}