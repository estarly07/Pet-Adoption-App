package com.estarly.petadoptionapp.domain.payment

import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.model.SelectPaymentModel
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllSelectPaymentsUseCase @Inject constructor(private val getAllPaymentsUseCase: GetAllPaymentsUseCase) {
    suspend operator fun invoke() : BaseResultUseCase<List<SelectPaymentModel>>{
        return try {
            when(val response = getAllPaymentsUseCase()){
                is BaseResultUseCase.Error -> BaseResultUseCase.Error(response.exception)
                BaseResultUseCase.NoInternetConnection -> BaseResultUseCase.NoInternetConnection
                BaseResultUseCase.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                is BaseResultUseCase.Success -> {
                    val list = mutableListOf<SelectPaymentModel>()
                    for ( index in response.data.indices){
                        list.add(SelectPaymentModel(
                            isCheck = index==0,
                            paymentModel = response.data[index]
                        ))
                    }
                    BaseResultUseCase.Success(list)
                }
            }
        }catch (e : Exception){
            BaseResultUseCase.Error(e)
        }
    }
}