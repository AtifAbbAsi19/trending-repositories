package com.inc.sada_pay_test.network.networkstates

sealed class ApiState<out R> {

    object Loading : ApiState<Nothing>()

    class Failure(val msg: String) : ApiState<Nothing>()

    class Success<out R>(val response: R) : ApiState<R>()

}