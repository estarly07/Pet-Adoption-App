package com.estarly.petadoptionapp.ui.home.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.estarly.petadoptionapp.domain.model.CategoryModel
import com.estarly.petadoptionapp.ui.CustomSlideDown
import com.estarly.petadoptionapp.ui.composables.CustomShimmerRectangleWait


@Composable
fun TagsHome(listTags: List<CategoryModel>, idSelectTag: Int, wait: Boolean, onCheckTag: (Int) -> Unit) {
    if (wait) {
        LazyRow {
            items(4) {
                CustomShimmerRectangleWait(
                    modifier = Modifier
                        .padding(end = 35.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .height(15.dp)
                        .width(40.dp)
                )
            }
        }
    } else {
        LazyRow(content = {
            itemsIndexed(listTags, key = { _, it-> it.id }) { index, it->
                CustomSlideDown(delay= 400+(index * 50)){
                    ItemTag(
                        tag           = it,
                        colorSelect   = MaterialTheme.colors.onPrimary,
                        colorUnSelect = MaterialTheme.colors.onSecondary,
                        modifier      = Modifier.padding(end = 35.dp),
                        isSelect      = it.id == idSelectTag,
                    ) { tag -> onCheckTag(tag.id) }
                }
            }
        })
    }
}

@Composable
fun ItemTag(
    tag          : CategoryModel,
    colorSelect  : Color,
    colorUnSelect: Color,
    modifier     : Modifier,
    isSelect     : Boolean,
    onTap        : (CategoryModel) -> Unit
) {
    with(tag) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.clickable { onTap(tag) }) {
            Text(nameTag, color = if (isSelect) colorSelect else colorUnSelect, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
            if (isSelect) {
                Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(colorSelect))
            }
        }
    }
}