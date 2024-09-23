package com.estarly.petadoptionapp.ui.home.views

import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.CustomSlideDown
import com.estarly.petadoptionapp.ui.CustomSlideLeft
import com.estarly.petadoptionapp.ui.composables.CustomButton
import com.estarly.petadoptionapp.ui.composables.CustomTextField


@Composable
fun SearchHome(context : Context, search: String, onClickFilter: () -> Unit, onTextChanged: (String) -> Unit) {
    Row {
        CustomSlideDown(delay = 250, modifier = Modifier.weight(1f)){
            CustomTextField(
                value           = search,
                modifier        = Modifier.fillMaxWidth(),
                onTextChanged   = onTextChanged,
                textColor       = MaterialTheme.colors.onSecondary,
                leadingIcon     = { Icon(imageVector = Icons.Sharp.Search, contentDescription = "Icon search", tint = MaterialTheme.colors.onSecondary) },
                placerHolder    = context.getString(R.string.search),
                backgroundColor = MaterialTheme.colors.secondary,
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        CustomSlideLeft(250){
            CustomButton(
                modifier   = Modifier.size(50.dp),
                onClick    = { onClickFilter() },
                composable = {
                    Icon(
                        painter  = painterResource(id = R.drawable.ic_filter),
                        tint     = Color.White,
                        modifier = Modifier.align(Alignment.Center).size(20.dp),
                        contentDescription = "button filter"
                    )
                },
            )
        }
    }
}