package com.estarly.petadoptionapp.base

sealed class BaseResultUseCase<out T : Any?> {
    class Success<out T: Any>(val data : T) : BaseResultUseCase<T>()
    class Error(val exception: Throwable) : BaseResultUseCase<Nothing>()
    object NoInternetConnection : BaseResultUseCase<Nothing>()
    object NullOrEmptyData : BaseResultUseCase<Nothing>()
}