package com.estarly.petadoptionapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.breeds.FilterBreedsUseCase
import com.estarly.petadoptionapp.domain.breeds.GetBreedsUseCase
import com.estarly.petadoptionapp.domain.breeds.SearchBreedsUseCase
import com.estarly.petadoptionapp.domain.categories.GetCategoriesUseCase
import com.estarly.petadoptionapp.domain.promotion.GetPromotionUseCase
import com.estarly.petadoptionapp.ui.model.BreedModel
import com.estarly.petadoptionapp.ui.model.PromotionModel
import com.estarly.petadoptionapp.ui.model.CategoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
  private val getPromotionUseCase: GetPromotionUseCase,
  private val getBreedsUseCase   : GetBreedsUseCase,
  private val searchBreedsUseCase: SearchBreedsUseCase,
  private val filterBreedsUseCase: FilterBreedsUseCase,
  private val getCategoriesUseCase: GetCategoriesUseCase
): ViewModel() {
    private val TAG ="HomeViewModel"

    private var _tags = MutableLiveData<List<CategoryModel>>()
    val tags: LiveData<List<CategoryModel>> = _tags
    private var _idSelectTag = MutableLiveData<Int>()
    val idSelectTag: LiveData<Int> = _idSelectTag
    private var _showDialogFilter = MutableLiveData<Boolean>()
    val showDialogFilter: LiveData<Boolean> = _showDialogFilter
    private var _isSearching= MutableLiveData<Boolean>()//show/fade multiple components to show only the list of breeds
    val isSearching : LiveData<Boolean> = _isSearching//show/fade multiple components to show only the list of breeds
    private val _search = MutableLiveData<String>()
    val search : LiveData<String> = _search
    val _breeds = MutableLiveData<List<BreedModel>?>()
    private var breedsSaveInfo : List<BreedModel>? = mutableListOf()
    val breeds : LiveData<List<BreedModel>?> = _breeds
    private val _showProgressPromotion = MutableLiveData<Boolean>()
    val showProgressPromotion : LiveData<Boolean> = _showProgressPromotion
    private  val _promotion = MutableLiveData<PromotionModel?>()
    val promotion : LiveData<PromotionModel?> = _promotion

    fun onCreate(){
        viewModelScope.launch{
            getPromotion()
            getBreeds()
            getCategories()
        }
    }

    private suspend fun getCategories() {
        val response = getCategoriesUseCase()
        when(response){
            is BaseResultUseCase.Error -> {}
            BaseResultUseCase.NoInternetConnection -> {}
            BaseResultUseCase.NullOrEmptyData -> {}
            is BaseResultUseCase.Success -> {
                _tags.value = response.data
                _tags.value!!.find { it.id == -1 }?.let {
                    changeSelectTag(it.id)
                }
            }
        }
    }

    private suspend fun getBreeds() {
        //TODO Show progress
        val response = getBreedsUseCase()
        //TODO gone progress
        when(response){
            is BaseResultUseCase.Error -> {}
            BaseResultUseCase.NoInternetConnection -> {}
            BaseResultUseCase.NullOrEmptyData -> {}
            is BaseResultUseCase.Success -> {
                _breeds.value  = response.data
                breedsSaveInfo = response.data
            }
        }
    }

    private suspend fun getPromotion() {
        _showProgressPromotion.value = true// show progress promotion
        val responsePromotion = getPromotionUseCase()// get promotion
        Log.i(TAG,responsePromotion.toString())
        _showProgressPromotion.value = false// gone progress promotion
        when(responsePromotion){
            is BaseResultUseCase.Error -> {}
            BaseResultUseCase.NoInternetConnection -> {}
            BaseResultUseCase.NullOrEmptyData -> {}
            is BaseResultUseCase.Success -> _promotion.value = responsePromotion.data
        }
    }
    fun searchBread(word : String){
        _isSearching.value = word.isEmpty()//TODO change for sqlite
        _search.value = word
        _breeds.value = searchBreedsUseCase(breedsSaveInfo,word)
    }

    fun showDialogFilter(show: Boolean = true) { _showDialogFilter.value = show }

    fun filter(attribute: String? = null, idCategory: Int? = null) {
        _idSelectTag.value = idCategory ?: _idSelectTag.value
        _breeds.value = filterBreedsUseCase(attribute,idCategory,breedsSaveInfo ?: listOf())
    }

    fun changeSelectTag(id: Int) {
        _idSelectTag.value = id
        filter(idCategory = id)
    }
}