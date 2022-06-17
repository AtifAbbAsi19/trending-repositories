package com.inc.sada_pay_test.network

import com.inc.sada_pay_test.network.networkstates.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

fun <T> baseApiResultHandler(call: suspend () -> Response<T>): Flow<ApiState<T?>> = flow {

    emit(ApiState.Loading)

    try {

        val call_ = call()
        if (call_.isSuccessful) {
            emit(ApiState.Success(call_.body()))
        } else {
            call_.errorBody().let { error ->
                error?.close()
                emit(ApiState.Failure(error.toString()))
            }
        }

    } catch (e: Exception) {
        e.printStackTrace()
        emit(ApiState.Failure(e.message.toString()))
    }

}.flowOn(Dispatchers.IO)

