package com.mrtnmrls.tvmazeapi.data.network

sealed class NetworkResultState<T: Any> {
        class Success<T: Any>(val data: T): NetworkResultState<T>()
        class Loading<T: Any>: NetworkResultState<T>()
        class Error<T: Any>(val code: Int, val message: String?) : NetworkResultState<T>()
}