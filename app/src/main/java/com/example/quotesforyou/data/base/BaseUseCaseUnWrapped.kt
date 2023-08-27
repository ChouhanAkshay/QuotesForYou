package com.example.quotesforyou.data.base

import kotlinx.coroutines.flow.Flow

abstract class BaseUseCaseUnWrapped<out O : ApiResponse> {
    abstract suspend fun processRequest() : Flow<Resource<O>>
}

abstract class BaseUseCaseUnWrappedWithInput<in input : AppResponse, out output : ApiResponse> {
    abstract suspend fun processRequest(inputData : input) : Flow<Resource<output>>
}