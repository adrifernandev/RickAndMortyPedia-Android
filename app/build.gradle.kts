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
    implementation(project(":designsystem"))
}