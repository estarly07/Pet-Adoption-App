package com.estarly.petadoptionapp.ui.home.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.navigators.MainRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel(){
    private var _idSelectNavigationBar = MutableLiveData<Int>()
    val idSelectNavigationBar : LiveData<Int> = _idSelectNavigationBar

    val listButtonsNavigation = listOf(
        Pair(R.drawable.ic_home     ,0),
        Pair(R.drawable.ic_store    ,1),
        Pair(R.drawable.ic_favorite ,2),
        Pair(R.drawable.ic_user     ,3),
    )
    fun changeScreen(idNavigationBar: Int, navController: NavHostController){
        _idSelectNavigationBar.value = idNavigationBar
        when(idNavigationBar){
            0 -> navController.navigate(MainRoute.HomeScreen.route)
            1 -> navController.navigate(MainRoute.StoreScreen.route)
            2 -> navController.navigate(MainRoute.HomeScreen.route)
            3 -> navController.navigate(MainRoute.HomeScreen.route)
        }

    }

}