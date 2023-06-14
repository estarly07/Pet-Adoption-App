package com.estarly.petadoptionapp.ui.payment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.model.SelectPaymentModel
import com.estarly.petadoptionapp.domain.payment.GetAllSelectPaymentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectPaymentViewModel @Inject constructor(
    private val getAllSelectPaymentsUseCase : GetAllSelectPaymentsUseCase
): ViewModel() {
    private val _listPayments = MutableLiveData<List<SelectPaymentModel>>()
    val listPayments : LiveData<List<SelectPaymentModel>> = _listPayments

    fun getListPayments(){
        viewModelScope.launch {
            when(val response = getAllSelectPaymentsUseCase()){
                is BaseResultUseCase.Error -> TODO()
                BaseResultUseCase.NoInternetConnection -> TODO()
                BaseResultUseCase.NullOrEmptyData -> TODO()
                is BaseResultUseCase.Success -> _listPayments.value = response.data
            }

        }
    }

    fun checkPayment(selectPayment: SelectPaymentModel) {
        val list = mutableListOf<SelectPaymentModel>()
        Log.i("TAG","${list.size}")
        _listPayments.value?.forEach {
            list.add(it)
            return@forEach
        }
        _listPayments.value = listOf()
        list.forEach {
            it.apply { isCheck = it.paymentModel.id == selectPayment.paymentModel.id }
        }
        Log.i("TAG","${list.size}")
        _listPayments.postValue(list)
    }
}