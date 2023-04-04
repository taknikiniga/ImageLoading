package com.taknikiniga.networking.networking.utils

sealed class NetworkingState<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : NetworkingState<T>(data)
    class Loading<T>(data: T? = null) : NetworkingState<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : NetworkingState<T>(data, throwable)
}
