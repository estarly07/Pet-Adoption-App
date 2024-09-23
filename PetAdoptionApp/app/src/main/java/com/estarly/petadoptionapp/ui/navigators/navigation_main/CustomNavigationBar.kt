package com.estarly.petadoptionapp.ui.navigators.navigation_main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.estarly.petadoptionapp.ui.CustomScaleIn
import com.estarly.petadoptionapp.ui.composables.CustomButton
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight

@Composable
fun CustomBottomBar(idBottomSelect : Int, listBottomNavigationBar : List<Pair<Int,Int>>, onSelect:(Int)->Unit) {
    Box(modifier = Modifier.fillMaxWidth().background(Color.White).padding(horizontal = 35.dp)){
        BottomNavigation(
            modifier        = Modifier.height(70.dp),
            backgroundColor = Color.White,
            elevation       = 0.dp,
        ) {
            listBottomNavigationBar.forEachIndexed { _, it ->
                BottomNavigationItem(
                    selectedContentColor = Color.White,
                    selected = idBottomSelect == it.second,
                    onClick  = { onSelect(it.second) },
                    icon     = { IconNavigationBar(idBottomSelect, idBottom = it.second, drawable = it.first) },
                )
            }
        }
    }
}

@Composable
private fun IconNavigationBar(idBottomSelect: Int, idBottom: Int, @DrawableRes drawable :Int) {
    if (idBottomSelect == idBottom) {
        CustomScaleIn(duration = 150) {
            Column {
                CustomButton(
                    modifier   = Modifier.size(50.dp),
                    isCircle   = true,
                    composable = {
                        Icon(
                            painter  = painterResource(id =  drawable),
                            tint     = Color.White,
                            modifier = Modifier.align(Alignment.Center),
                            contentDescription = "IconNavigationBar",
                        )
                    }
                ) {}
                CustomSpaceHeight(height = 20.dp)
            }
        }
    } else {
        Icon(
            painter = painterResource(id =  drawable),
            tint    = MaterialTheme.colors.onSecondary,
            contentDescription = "IconNavigationBar",
        )
    }
}