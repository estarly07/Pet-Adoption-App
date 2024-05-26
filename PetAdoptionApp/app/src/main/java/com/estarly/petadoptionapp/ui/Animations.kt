package com.estarly.petadoptionapp.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

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
/**
 *
 * Anima un composable con una transición de desvanecimiento (fade-in) con una duración y un retraso especificados.
 *
 * Esta función composable aplica una animación de desvanecimiento (fade-in) a un composable.
 * La animación tiene una duración y un retraso especificados
 *
 * @param delay El retraso en milisegundos antes de comenzar la animación de desvanecimiento (por defecto es 0).
 * @param duration La duración de la animación de desvanecimiento en milisegundos.
 * @param modifier Un modificador para el composable (por defecto es `Modifier`).
 * @param composable El composable que se va a animar.
 */
@Composable
fun CustomFadeIn(delay : Int = 0,duration : Int, modifier: Modifier = Modifier, composable : @Composable AnimatedVisibilityScope.() -> Unit){
  CustomAnimatedVisibility(modifier = modifier, enterTransition = fadeIn(tween(duration,delay)),composable = composable)
}
/**
 *
 * Anima un composable con una transición de deslizamiento hacia arriba y desvanecimiento.
 *
 * Esta función `CustomSlideUp` proporciona una animación personalizada para un composable.
 * La animación incluye una transición que combina un desvanecimiento (`fadeIn`) y un
 * deslizamiento vertical hacia arriba (`slideInVertically`). Puedes especificar un retraso
 * en milisegundos antes de que la animación comience usando el parámetro `delay`.
 *
 * @param delay El retraso antes de que la animación comience, en milisegundos (por defecto es 0).
 * @param modifier El modificador para aplicar al composable (por defecto es Modifier).
 * @param composable El contenido del composable que será animado.
 *
 */
@Composable
fun CustomSlideUp(delay : Int = 0, modifier: Modifier = Modifier, composable : @Composable AnimatedVisibilityScope.() -> Unit){
    CustomAnimatedVisibility(
        modifier        = modifier,
        enterTransition = fadeIn (tween(500,delay)) + slideInVertically (initialOffsetY = { 100 }, animationSpec= tween(500,delay)),
        composable      = composable
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CustomAnimateNumberUpOrDown(
    count : Int,
    content : @Composable AnimatedVisibilityScope.(targetState: Int) ->Unit
){
    AnimatedContent(
        targetState = count,
        transitionSpec = {
            if (targetState > initialState) {
                slideInVertically { height -> height } + fadeIn() with
                        slideOutVertically { height -> -height } + fadeOut()
            } else {
                slideInVertically { height -> -height } + fadeIn() with
                        slideOutVertically { height -> height } + fadeOut()
            }.using(
                SizeTransform(clip = false)
            )
        },
        content = content

    )
}
@Composable
fun CustomAnimateExpandBounce(
    content : @Composable BoxScope.() ->Unit
){
   Box(
       modifier = Modifier
           .animateContentSize(
               animationSpec = spring(
                   dampingRatio = Spring.DampingRatioMediumBouncy,
                   stiffness = Spring.StiffnessLow
               )
           ),
       content = content
   )
}
