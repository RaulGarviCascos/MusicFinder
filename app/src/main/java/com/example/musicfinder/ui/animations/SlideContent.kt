package com.example.musicfinder.ui.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

@Composable
fun SlideContent(visible:Boolean, topPadding: PaddingValues, enterDirection: Int, content: @Composable (PaddingValues) -> Unit){
    AnimatedVisibility(
        visible = visible,
        enter =
        slideInHorizontally(animationSpec = tween(durationMillis = 200)) { fullWidth ->
            fullWidth / 3*enterDirection
        } +
                fadeIn(
                    animationSpec = tween(durationMillis = 200)
                ),
        exit =
        slideOutHorizontally(animationSpec = spring(stiffness = Spring.StiffnessHigh)) {
            200*enterDirection
        } + fadeOut()
    ) {
        content(topPadding)
    }
}