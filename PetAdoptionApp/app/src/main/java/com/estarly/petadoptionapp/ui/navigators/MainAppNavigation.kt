package com.estarly.petadoptionapp.ui.navigators

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.estarly.petadoptionapp.ui.home.screens.HomeScreen
import com.estarly.petadoptionapp.ui.home.viewmodels.HomeViewModel
import com.estarly.petadoptionapp.ui.store.StoreScreen
import com.estarly.petadoptionapp.ui.store.StoreViewModel

/**
 *
 * @param homeViewModel ViewModel asociado a la pantalla de inicio.
 * @param storeViewModel ViewModel asociado a la pantalla de la tienda.
 * @param navController Controlador de navegación para gestionar las rutas de la aplicación.
 *
 * Configura la navegación principal del aplicativo con rutas.
 *
 * El composable `MainAppNavigation` establece la estructura de navegación principal del aplicativo. Utiliza
 * un `NavHostController` para definir y gestionar las rutas de navegación. Las rutas se definen en la clase
 * sellada `MainRoute`. Cada ruta está asociada a un composable específico (`HomeScreen` y `StoreScreen`) y utiliza los correspondientes ViewModels (`homeViewModel`
 * y `storeViewModel`) para gestionar el estado y la lógica de negocio de cada pantalla.
 */
@Composable
fun MainAppNavigation(
    homeViewModel : HomeViewModel,
    storeViewModel: StoreViewModel,
    navController : NavHostController
){
    NavHost(navController = navController, startDestination = MainRoute.HomeScreen.route){
        composable(MainRoute.HomeScreen.route) { HomeScreen(homeViewModel = homeViewModel) }
        composable(MainRoute.StoreScreen.route){StoreScreen(storeViewModel)}
    }
}
/**
 * Clase sellada que define las rutas principales de la aplicación.
 *
 * @property route La ruta de navegación como una cadena de texto.
 */
sealed class MainRoute(val route: String){
    object HomeScreen  : MainRoute("screenHome")
    object StoreScreen : MainRoute("screenStore")
}