package com.estarly.petadoptionapp.ui.home.navigation

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
import com.estarly.petadoptionapp.ui.CustomFadeIn
import com.estarly.petadoptionapp.ui.composables.CustomButton
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight

@Composable
fun CustomBottomBar(
    idBottomSelect : Int,
    listBottomNavigationBar : List<Pair<Int,Int>>,
    onSelect:(Int)->Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 35.dp)
    ){
        BottomNavigation(
            modifier = Modifier.height(70.dp),
            backgroundColor = Color.White,
            elevation = 0.dp,
        ) {
            listBottomNavigationBar.forEachIndexed { index, it ->
                BottomNavigationItem(
                    selected = idBottomSelect == it.second,
                    selectedContentColor = Color.White,
                    onClick = { onSelect(it.second) },
                    icon = {
                        if (idBottomSelect == it.second) {
                            CustomFadeIn(duration = 500) {
                                Column {
                                    CustomButton(
                                        isCircle = true,
                                        modifier = Modifier
                                            .size(50.dp),
                                        composable = {
                                            Icon(
                                                painter = painterResource(id =  it.first),
                                                contentDescription = "",
                                                tint = Color.White,
                                                modifier = Modifier.align(Alignment.Center)
                                            )
                                        }
                                    ) {}
                                    CustomSpaceHeight(height = 20.dp)
                                }
                            }
                        } else {
                            Icon(
                                painter = painterResource(id =  it.first),
                                contentDescription = "",
                                tint = MaterialTheme.colors.onSecondary,
                            )
                        }
                    },
                )
            }
        }
    }
}
