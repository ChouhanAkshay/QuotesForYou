package com.example.quotesforyou.data.service

import com.example.quotesforyou.data.model.response.Quote
import retrofit2.Response
import retrofit2.http.GET


interface Service {
    @GET("/random")
    suspend fun getRandomQuote() : Response<Quote>
}