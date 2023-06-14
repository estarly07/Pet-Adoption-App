package com.estarly.petadoptionapp.domain.payment

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.PaymentRepository
import com.estarly.petadoptionapp.domain.model.PaymentModel
import com.estarly.petadoptionapp.domain.user.GetUserUseCase
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllPaymentsUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository,
    private val getUserUseCase: GetUserUseCase
) {
    suspend operator fun invoke() : BaseResultUseCase<List<PaymentModel>>{
        return try {
            when(val responseUser = getUserUseCase()){
                is BaseResultUseCase.Error -> BaseResultUseCase.Error(responseUser.exception)
                BaseResultUseCase.NoInternetConnection -> BaseResultUseCase.NoInternetConnection
                BaseResultUseCase.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                is BaseResultUseCase.Success -> {
                    when(val response = paymentRepository.getPayments(responseUser.data.id)){
                        is BaseResultRepository.Error -> BaseResultUseCase.Error(response.exception)
                        BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                        is BaseResultRepository.Success -> BaseResultUseCase.Success(response.data)
                    }
                }
            }
        }catch (e : Exception){
            BaseResultUseCase.Error(e)
        }
    }
}