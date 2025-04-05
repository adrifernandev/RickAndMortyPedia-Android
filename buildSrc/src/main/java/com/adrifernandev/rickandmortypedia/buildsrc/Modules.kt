package com.adrifernandev.rickandmortypedia.buildsrc

object Modules {
    private const val COMMON_MODULES = ":common"
    private const val FEATURE_MODULES = ":feature"

    private const val HOME_MODULES = ":home"
    private const val CHARACTER_MODULES = ":character"
    private const val LOCATION_MODULES = ":location"
    private const val CHARACTER_DETAIL_MODULES = ":characterdetail"

    private const val LAYER_PRESENTATION = ":presentation"
    private const val LAYER_DOMAIN = ":domain"
    private const val LAYER_DATA = ":data"

    const val COMMON_DI = "$COMMON_MODULES:di"
    const val COMMON_FUNDAMENTS = "$COMMON_MODULES:fundaments"
    const val COMMON_DATA = "$COMMON_MODULES$LAYER_DATA"

    const val FEATURE_CHARACTER_DATA = "$FEATURE_MODULES$CHARACTER_MODULES$LAYER_DATA"
    const val FEATURE_CHARACTER_DOMAIN = "$FEATURE_MODULES$CHARACTER_MODULES$LAYER_DOMAIN"
    const val FEATURE_CHARACTER_DETAIL_PRESENTATION =
        "$FEATURE_MODULES$CHARACTER_DETAIL_MODULES$LAYER_PRESENTATION"
    const val FEATURE_CHARACTER_PRESENTATION =
        "$FEATURE_MODULES$CHARACTER_MODULES$LAYER_PRESENTATION"

    const val FEATURE_HOME_PRESENTATION = "$FEATURE_MODULES$HOME_MODULES$LAYER_PRESENTATION"

    const val FEATURE_LOCATION_DOMAIN = "$FEATURE_MODULES$LOCATION_MODULES$LAYER_DOMAIN"

    const val COMMON_PRESENTATION = "$COMMON_MODULES$LAYER_PRESENTATION"
    const val DESIGN_SYSTEM = ":designsystem"
    const val APP = ":app"
}