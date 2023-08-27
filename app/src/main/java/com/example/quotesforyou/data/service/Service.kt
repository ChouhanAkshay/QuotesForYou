package com.example.quotesforyou.data.service

import com.example.quotesforyou.data.model.response.Quote
import com.example.quotesforyou.data.model.response.QuotesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface Service {
    @GET("/random")
    suspend fun getRandomQuote() : Response<Quote>

    @GET("/quotes")
    suspend fun getQuotesList(
        @QueryMap map : HashMap<String,Any>
    ) : Response<QuotesList>
}