package com.example.quotesforyou.data.base

abstract class Mapper<I : ApiResponse,O : AppResponse> {
    abstract fun map(input : I) : O
}