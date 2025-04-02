import com.adrifernandev.rickandmortypedia.buildsrc.Versions
import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
}

detekt {
    basePath = rootProject.projectDir.path
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = Versions.JVM_TARGET
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    reports {
        sarif.required.set(true)
    }
}