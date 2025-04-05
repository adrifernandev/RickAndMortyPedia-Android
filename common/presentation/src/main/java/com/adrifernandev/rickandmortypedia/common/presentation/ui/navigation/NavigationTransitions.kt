package com.adrifernandev.rickandmortypedia.common.presentation.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween

private const val DEFAULT_ANIMATION_DURATION_IN_MILLIS = 500

fun getExitTransition(
    direction: AnimatedContentTransitionScope.SlideDirection,
    durationMillis: Int = DEFAULT_ANIMATION_DURATION_IN_MILLIS
): (AnimatedContentTransitionScope<*>.() -> ExitTransition?) {
    return {
        slideOutOfContainer(
            towards = direction,
            animationSpec = tween(durationMillis)
        )
    }
}

fun getEnterTransition(
    direction: AnimatedContentTransitionScope.SlideDirection,
    durationMillis: Int = DEFAULT_ANIMATION_DURATION_IN_MILLIS
): (AnimatedContentTransitionScope<*>.() -> EnterTransition?) {
    return {
        slideIntoContainer(
            towards = direction,
            animationSpec = tween(durationMillis)
        )
    }
}