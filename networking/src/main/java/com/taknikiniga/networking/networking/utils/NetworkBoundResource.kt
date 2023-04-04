package com.taknikiniga.networking.networking.utils

import android.util.Log
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(NetworkingState.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { NetworkingState.Success(it) }
        } catch (throwable: Throwable) {
            query().map { NetworkingState.Error(throwable, it) }
        }
    } else {
        query().map { NetworkingState.Success(it) }
    }

    emitAll(flow)
}