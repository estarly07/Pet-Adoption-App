package com.estarly.petadoptionapp.ui.dialog.filter

import android.widget.GridLayout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import com.estarly.petadoptionapp.ui.theme.*

@Composable
fun CustomDialogFilter(
    items: List<String>,
    show: Boolean,
    onDismiss: () -> Unit,
    onApply: (attribute : String, category : String) -> Unit,
) {
    if (show) {
        var selectItem by rememberSaveable { mutableStateOf("") }
        var selectTag by rememberSaveable { mutableStateOf("")}
        Dialog(
            onDismissRequest = { onDismiss() },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White)
                    .padding(top = 25.dp, bottom = 5.dp)
            ) {
                HeaderDialog { onDismiss() }
                CustomSpaceHeight(height = 10.dp)
                Options(items, selectItem) { item -> selectItem = item }
                CustomSpaceHeight(height = 10.dp)
                Tags(listOf("All", "Cats", "Dogs", "Birds"), selectTag) {selectTag = it}
                CustomSpaceHeight(height = 15.dp)
                Divider(
                    color = TextColor,
                    modifier = Modifier
                        .height(1.dp)
                        .padding(horizontal = 5.dp)
                )
                Actions(Modifier.align(Alignment.End)) {
                    onDismiss()
                    onApply(selectItem,selectTag)
                }

            }
        }
    }
}

@Composable
fun Tags(list: List<String>, selectItem: String, onClickTag: (String) -> Unit) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        itemsIndexed(list) { index, it ->
            OutlinedButton(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.padding(
                    start = if (index == 0) MarginHorizontalScreen else 0.dp,
                    end = if (index == list.size - 1) MarginHorizontalScreen else 0.dp,
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectItem == it) Pink else Color.Transparent
                ),
                border = BorderStroke(2.dp, Pink),
                onClick = { onClickTag(it) }
            ) {
                Text(
                    text = it,
                    fontSize = 13.sp,
                    color = if (selectItem == it) Color.White else TextColor,
                    modifier = Modifier.padding(vertical =  if(selectItem == it) 3.dp else 0.dp),
                    fontWeight = if (selectItem == it) FontWeight.Bold else null
                )
            }
        }
    }

}

@Composable
fun Actions(modifier: Modifier, onConfirm: () -> Unit) {
    TextButton(onClick = onConfirm, modifier = modifier.padding(end = MarginHorizontalScreen)) {
        Text(text = "Aplicar", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Pink)
    }
}

@Composable
fun Options(
    items: List<String>,
    selectItem: String,
    onCheck: (String) -> Unit
) {
    items.forEachIndexed { _, item ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MarginHorizontalScreen),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectItem == item,
                onClick = { onCheck(item) },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Pink,
                    unselectedColor = TextColor
                )
            )
            Text(
                text = item,
                fontSize = if (selectItem == item) 17.sp else 15.sp,
                color = TextColor,
                fontWeight = if (selectItem == item) FontWeight.Bold else null
            )
        }
    }
}

@Composable
fun HeaderDialog(onClose: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MarginHorizontalScreen),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Filtro de búsqueda", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Icon(
            imageVector = Icons.Sharp.Close,
            contentDescription = "icon close",
            modifier = Modifier.clickable { onClose() })
    }
}
