package com.estarly.petadoptionapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.rememberNavController
import com.estarly.petadoptionapp.ui.home.viewmodels.HomeViewModel
import com.estarly.petadoptionapp.ui.navigators.navigation_main.CustomBottomBar
import com.estarly.petadoptionapp.ui.navigators.navigation_main.NavigationViewModel
import com.estarly.petadoptionapp.ui.navigators.MainAppNavigation
import com.estarly.petadoptionapp.ui.store.StoreViewModel
import com.estarly.petadoptionapp.ui.theme.PetAdoptionAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), ActivityStructure{
    private val homeViewModel       : HomeViewModel by viewModels()
    private val navigationViewModel : NavigationViewModel by viewModels()
    private val storeViewModel      : StoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        getData()
    }
    /**
     * Inicializa las vistas de la pantalla.
     *
     * Esta función se utiliza para configurar e inicializar todos los elementos visuales (views) de la pantalla.
     * Se llama generalmente en el proceso de creación de la pantalla para asegurarse de que todas las vistas
     * están correctamente configuradas antes de interactuar con ellas.
     */
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun initView() {
        setContent {
            val idSelectButton by navigationViewModel.idSelectNavigationBar.observeAsState(initial = 0)
            val navController = rememberNavController()
            PetAdoptionAppTheme(false) {
                Scaffold(
                    bottomBar = { CustomBottomBar(idSelectButton,navigationViewModel.listButtonsNavigation){
                        navigationViewModel.changeScreen(it,navController)
                    } }
                ) {
                    MainAppNavigation(homeViewModel,storeViewModel,navController)
                }
            }
        }
    }
    /**
     *
     * Esta función recupera la información necesaria para mostrar en la Activity.
     *
     * Esta función es utilizada para obtener los datos que serán presentados en la Activity.
     * La función encapsula la lógica de recuperación de datos desde las fuentes correspondientes,
     * que pueden incluir bases de datos locales, servicios web, entre otro
     *
     */
    override fun getData() {
        homeViewModel.onCreate()
        storeViewModel.onCreate()
    }
    override fun onResume() {
        super.onResume()
        storeViewModel.getCantProductsCart()
    }
}


