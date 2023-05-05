package com.pbl.timekeeping.base.network

public open class NetworkErrorException (val responseMessage: String? = null): Exception() {
}