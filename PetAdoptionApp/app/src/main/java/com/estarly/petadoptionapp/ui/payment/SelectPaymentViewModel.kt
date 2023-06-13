package com.estarly.petadoptionapp.ui.payment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estarly.petadoptionapp.domain.model.PaymentModel
import com.estarly.petadoptionapp.domain.model.SelectPaymentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectPaymentViewModel @Inject constructor(): ViewModel() {
    private val _listPayments = MutableLiveData<List<SelectPaymentModel>>()
    val listPayments : LiveData<List<SelectPaymentModel>> = _listPayments

    fun getListPayments(){
        viewModelScope.launch {
            val list = mutableListOf<SelectPaymentModel>()
            list.add(SelectPaymentModel(
                false,
                PaymentModel(
                    1,
                    "Mastercard",
                    "1005483645"
                )
            ))
            list.add(SelectPaymentModel(
                false,
                PaymentModel(
                    2,
                    "Visa",
                    "1005483645"
                )
            ))
            _listPayments.value = list
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