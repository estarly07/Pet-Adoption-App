package com.estarly.petadoptionapp.ui.home.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.estarly.petadoptionapp.ui.*
import com.estarly.petadoptionapp.ui.breed.BreedActivity
import com.estarly.petadoptionapp.ui.composables.*
import com.estarly.petadoptionapp.ui.dialog.filter.CustomDialogFilter
import com.estarly.petadoptionapp.domain.model.BreedModel
import com.estarly.petadoptionapp.domain.model.PromotionModel
import com.estarly.petadoptionapp.domain.model.CategoryModel
import com.estarly.petadoptionapp.domain.model.UserModel
import com.estarly.petadoptionapp.ui.home.viewmodels.HomeViewModel
import com.estarly.petadoptionapp.ui.home.views.BreedsHome
import com.estarly.petadoptionapp.ui.home.views.HeaderHome
import com.estarly.petadoptionapp.ui.home.views.PromotionCardHome
import com.estarly.petadoptionapp.ui.home.views.SearchHome
import com.estarly.petadoptionapp.ui.home.views.TagsHome
import com.estarly.petadoptionapp.ui.home.views.TitleAndViewAllHome
import com.estarly.petadoptionapp.ui.theme.*
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val context = LocalContext.current
    val showProgressPromotion: Boolean by homeViewModel.showProgressPromotion.observeAsState(initial = true)
    val showProgressBreeds   : Boolean by homeViewModel.showProgressBreeds.observeAsState(initial = true)
    val showProgressTags     : Boolean by homeViewModel.showProgressCategories.observeAsState(initial = true)
    val promotion            : PromotionModel? by homeViewModel.promotion.observeAsState(initial = null)
    val breeds               : List<BreedModel>? by homeViewModel.breeds.observeAsState(initial = null)
    val search               : String by homeViewModel.search.observeAsState(initial = "")
    val isSearching          : Boolean by homeViewModel.isSearching.observeAsState(initial = true)
    val idSelectTag          : Int by homeViewModel.idSelectTag.observeAsState(initial = 0)
    val tags                 : List<CategoryModel> by homeViewModel.tags.observeAsState(initial = listOf())
    val showDialogFilter     : Boolean by homeViewModel.showDialogFilter.observeAsState(initial = false)
    val user                 : UserModel? by homeViewModel.user.observeAsState(initial = null)
    Scaffold{
        Column(Modifier.verticalScroll(rememberScrollState()).padding(horizontal = MarginHorizontalScreen)) {
            CustomSpaceHeight(height = 25.dp)
            HeaderHome(context = context, user = user)
            CustomSpaceHeight(height = 25.dp)
            SearchHome(context = context,search = search, onClickFilter = { homeViewModel.showDialogFilter() }) { homeViewModel.searchBread(it) }
            CustomSpaceHeight(height = 28.dp)
            if (isSearching) {
                TagsHome(listTags = tags, idSelectTag = idSelectTag,wait = showProgressTags) { id -> homeViewModel.changeSelectTag(id) }
                CustomSpaceHeight(height = 20.dp)
                PromotionCardHome(promotion, showProgressPromotion)
                CustomSpaceHeight(height = 20.dp)
                TitleAndViewAllHome()
                CustomSpaceHeight(height = 17.dp)
            }
            BreedsHome(breeds, wait = showProgressBreeds) { breed ->
                val intent = Intent(context,BreedActivity::class.java)
                intent.putExtra("breed",breed)
                context.startActivity(intent)
            }
            //Dialogs
            CustomDialogFilter(
                items = listOf("Nombre", "Cantidad"),
                categories = tags,
                show = showDialogFilter,
                onDismiss = { homeViewModel.showDialogFilter(false) }
            ) { attribute, idCategory ->
                homeViewModel.filter(attribute, idCategory)
            }
        }
    }
}
