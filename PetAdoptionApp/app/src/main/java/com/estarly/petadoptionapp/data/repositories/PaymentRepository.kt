package com.estarly.petadoptionapp.data.repositories

import android.util.Log
import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.data.api.firebase.Firebase
import com.estarly.petadoptionapp.data.api.response.PaymentResponse
import com.estarly.petadoptionapp.domain.model.PaymentModel
import com.estarly.petadoptionapp.domain.model.PaymentsTypes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRepository @Inject constructor(
    private val firebase: Firebase
) {
    suspend fun getPayments(idUser: String) : BaseResultRepository<List<PaymentModel>>{
        return try {
            val response = firebase.getPayments(idUser)
            if(response.data == null){
                BaseResultRepository.NullOrEmptyData
            }else{
                Log.i("getPayments",response.data!!.toString())
                val payments = mutableListOf<PaymentModel>()
                (response.data!!["list"] as List<*>).forEach {
                    payments.add(PaymentResponse.fromJson(it as Map<String, *>).toData())
                }
                BaseResultRepository.Success(payments)
            }
        }catch (e: Exception){
            BaseResultRepository.Error(e)
        }
    }
}
fun PaymentResponse.toData() : PaymentModel{
    return PaymentModel(
        id = id,
        type = when(type){
            1 -> PaymentsTypes.VISA
            else -> PaymentsTypes.MASTERCARD
        },
        number = number
    )
}