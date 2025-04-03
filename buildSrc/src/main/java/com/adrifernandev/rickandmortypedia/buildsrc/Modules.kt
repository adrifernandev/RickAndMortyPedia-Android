package com.adrifernandev.rickandmortypedia.buildsrc

object Modules {
    private const val COMMON_MODULES = ":common"
    private const val FEATURE_MODULES = ":feature"

    private const val HOME_MODULES = ":home"

    const val FEATURE_HOME_DATA = "$FEATURE_MODULES$HOME_MODULES:data"
    const val FEATURE_HOME_PRESENTATION = "$FEATURE_MODULES$HOME_MODULES:presentation"
    const val COMMON_PRESENTATION = "$COMMON_MODULES:presentation"
    const val DESIGN_SYSTEM = ":designsystem"
    const val APP = ":app"
}