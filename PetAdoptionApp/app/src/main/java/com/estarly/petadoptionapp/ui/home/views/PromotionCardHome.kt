package com.estarly.petadoptionapp.ui.home.views

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.estarly.petadoptionapp.domain.model.PromotionModel
import com.estarly.petadoptionapp.ui.composables.CustomCard
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import java.util.Locale


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PromotionCardHome(promotion: PromotionModel?, showProgressPromotion: Boolean) {
    Log.i("PromotionCard", "${!showProgressPromotion} && ${promotion == null}")
    CustomCard(wait = showProgressPromotion, height = 140.dp) {
        promotion?.let {
            with(promotion) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onPrimary,
                            fontSize = 17.sp
                        )
                        CustomSpaceHeight(height = 5.dp)
                        Text(
                            text = "Upto $percentage% off",
                            color = MaterialTheme.colors.primary,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        CustomSpaceHeight(height = 5.dp)
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onSecondary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("Use code ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.primary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(code.uppercase(Locale.getDefault()))
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onSecondary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(" and grab offer")
                            }
                        })
                    }
                    GlideImage(
                        model = image,
                        contentDescription = "image promotion",
                        modifier = Modifier
                            .weight(1.5f)
                            .align(Alignment.Bottom)
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(start = 10.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
    }
}