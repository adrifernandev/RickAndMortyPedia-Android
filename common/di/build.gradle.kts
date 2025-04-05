import com.adrifernandev.rickandmortypedia.buildsrc.Modules.COMMON_DATA
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.COMMON_PRESENTATION
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.DESIGN_SYSTEM
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.FEATURE_CHARACTER_DATA
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.FEATURE_CHARACTER_DETAIL_PRESENTATION
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.FEATURE_CHARACTER_DOMAIN
import com.adrifernandev.rickandmortypedia.buildsrc.Modules.FEATURE_HOME_PRESENTATION
import com.adrifernandev.rickandmortypedia.buildsrc.Versions

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "${Versions.APPLICATION_ID}.common.di"
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
}

dependencies {
    implementation(libs.androidx.core.ktx)

    implementation(project(DESIGN_SYSTEM))
    implementation(project(COMMON_PRESENTATION))
    implementation(project(FEATURE_HOME_PRESENTATION))
    implementation(project(FEATURE_CHARACTER_DATA))
    implementation(project(FEATURE_CHARACTER_DOMAIN))
    implementation(project(COMMON_DATA))
    implementation(project(FEATURE_CHARACTER_DETAIL_PRESENTATION))
}