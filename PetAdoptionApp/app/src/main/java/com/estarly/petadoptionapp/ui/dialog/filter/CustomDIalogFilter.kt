package com.estarly.petadoptionapp.ui.dialog.filter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import com.estarly.petadoptionapp.ui.model.CategoryModel
import com.estarly.petadoptionapp.ui.theme.*

@Composable
fun CustomDialogFilter(
    items: List<String>,
    categories: List<CategoryModel>,
    show: Boolean,
    onDismiss: () -> Unit,
    onApply: (attribute : String, idCategory : Int) -> Unit,
) {
    if (show) {
        var selectItem by rememberSaveable { mutableStateOf("") }
        var selectTag by rememberSaveable { mutableStateOf<Int?>(null)}
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
                Tags(categories, selectTag) {selectTag = it}
                CustomSpaceHeight(height = 15.dp)
                Divider(
                    color = MaterialTheme.colors.onSecondary,
                    modifier = Modifier
                        .height(1.dp)
                        .padding(horizontal = 5.dp)
                )
                Actions(Modifier.align(Alignment.End)) {
                    if(selectItem.isEmpty() && selectTag == null){
                        return@Actions
                    }
                    onDismiss()
                    onApply(selectItem,selectTag!!)
                }

            }
        }
    }
}

@Composable
fun Tags(list: List<CategoryModel>, idSelectCategory: Int?, onClickTag: (Int) -> Unit) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        itemsIndexed(list) { index, it ->
            OutlinedButton(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.padding(
                    start = if (index == 0) MarginHorizontalScreen else 0.dp,
                    end = if (index == list.size - 1) MarginHorizontalScreen else 0.dp,
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (idSelectCategory == it.id) MaterialTheme.colors.primaryVariant else Color.Transparent
                ),
                border = BorderStroke(2.dp, MaterialTheme.colors.primaryVariant),
                onClick = { onClickTag(it.id) }
            ) {
                Text(
                    text = it.nameTag,
                    fontSize = 13.sp,
                    color = if (idSelectCategory == it.id) Color.White else MaterialTheme.colors.onSecondary,
                    modifier = Modifier.padding(vertical =  if(idSelectCategory == it.id) 3.dp else 0.dp),
                    fontWeight = if (idSelectCategory == it.id) FontWeight.Bold else null
                )
            }
        }
    }

}

@Composable
fun Actions(modifier: Modifier, onConfirm: () -> Unit) {
    TextButton(onClick = onConfirm, modifier = modifier.padding(end = MarginHorizontalScreen)) {
        Text(text = "Aplicar", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = MaterialTheme.colors.primaryVariant)
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
                    selectedColor = MaterialTheme.colors.primaryVariant,
                    unselectedColor = MaterialTheme.colors.onSecondary
                )
            )
            Text(
                text = item,
                fontSize = if (selectItem == item) 17.sp else 15.sp,
                color = MaterialTheme.colors.onSecondary,
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
