package com.estarly.petadoptionapp.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun CustomAnimatedVisibility(
    modifier: Modifier = Modifier,
    enterTransition: EnterTransition = fadeIn (animationSpec = TweenSpec(500)),
    composable : @Composable AnimatedVisibilityScope.() -> Unit
){
   val state = remember{
       MutableTransitionState(initialState = false).apply{
           targetState = true
       }
   }
    AnimatedVisibility(
        visibleState = state,
        enter = enterTransition,
        modifier = modifier,
        content = composable
    )
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CustomFadeIn(
    delay : Int = 0,
    duration : Int,
    modifier: Modifier = Modifier,
    composable : @Composable AnimatedVisibilityScope.() -> Unit
){
  CustomAnimatedVisibility(
      modifier = modifier,
      enterTransition = fadeIn (
          tween(duration,delay)
      ),
      composable = composable
  )
}
@Composable
fun CustomSlideUp(
    delay : Int = 0,
    modifier: Modifier = Modifier,
    composable : @Composable AnimatedVisibilityScope.() -> Unit
){
    CustomAnimatedVisibility(
        modifier = modifier,
        enterTransition =
            fadeIn (
                tween(500,delay)
            )+
            slideInVertically (
                initialOffsetY = { 100 },
                animationSpec= tween(500,delay)
            ),
        composable = composable
    )
}
@Composable
fun CustomSlideDown(
    delay : Int = 0,
    modifier: Modifier = Modifier,
    composable : @Composable AnimatedVisibilityScope.() -> Unit
){
    CustomAnimatedVisibility(
        modifier = modifier,
        enterTransition =
            fadeIn (
                tween(500,delay)
            )+
            slideInVertically (
                initialOffsetY = { -100 },
                animationSpec= tween(500,delay)
            ),
        composable = composable
    )
}
@Composable
fun CustomSlideLeft(
    delay : Int = 0,
    modifier: Modifier = Modifier,
    composable : @Composable AnimatedVisibilityScope.() -> Unit
){
    CustomAnimatedVisibility(
        modifier = modifier,
        enterTransition =
            fadeIn (
                tween(500,delay)
            )+
            slideInHorizontally (
                initialOffsetX = { 50 },
                animationSpec = tween(500,delay)
            ),
        composable = composable
    )
}
@Composable
fun CustomSlideRight(
    delay : Int = 0,
    modifier: Modifier = Modifier,
    composable : @Composable AnimatedVisibilityScope.() -> Unit
){
    CustomAnimatedVisibility(
        modifier = modifier,
        enterTransition =
            fadeIn (
                tween(500,delay)
            )+
            slideInHorizontally (
                initialOffsetX = { -50 },
                animationSpec = tween(500,delay)
            ),
        composable = composable
    )
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CustomScaleIn(
    delay : Int = 0,
    modifier: Modifier = Modifier,
    composable : @Composable AnimatedVisibilityScope.() -> Unit
){
    CustomAnimatedVisibility(
        modifier = modifier,
        enterTransition =
            fadeIn (
                tween(500,delay)
            )+
            scaleIn (
                animationSpec = tween(500,delay)
            ),
        composable = composable
    )
}