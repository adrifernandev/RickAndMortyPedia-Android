import com.adrifernandev.rickandmortypedia.buildsrc.Modules.COMMON_PRESENTATION
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.DESIGN_SYSTEM
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.FEATURE_CHARACTER_DOMAIN
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.FEATURE_CHARACTER_PRESENTATION
import com.adrifernandev.rickandmortypedia.buildsrc.Versions

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
}

android {
    namespace = "${Versions.APPLICATION_ID}.feature.home.presentation"
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = Versions.javaVersion
        targetCompatibility = Versions.javaVersion
    }
    kotlinOptions {
        jvmTarget = Versions.JVM_TARGET
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.bundles.layer.presentation)
    implementation(platform(libs.androidx.compose.bom))
    ksp(libs.bundles.compilers.ksp.generic)

    //Modules implementation
    implementation(project(DESIGN_SYSTEM))
    implementation(project(COMMON_PRESENTATION))
    implementation(project(FEATURE_CHARACTER_DOMAIN))
    implementation(project(FEATURE_CHARACTER_PRESENTATION))
}