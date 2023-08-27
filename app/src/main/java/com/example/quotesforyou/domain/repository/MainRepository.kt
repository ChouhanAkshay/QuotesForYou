package com.example.quotesforyou.domain.repository

import android.content.res.Resources
import com.example.quotesforyou.data.base.Resource
import com.example.quotesforyou.data.model.request.GetQuotes
import com.example.quotesforyou.data.model.response.Quote
import com.example.quotesforyou.data.model.response.QuotesList
import com.example.quotesforyou.domain.model.QuoteData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MainRepository {
    suspend fun getQuotes() : Flow<Resource<Quote>>
    suspend fun getQuotesList(inputData : GetQuotes): Flow<Resource<QuotesList>>
}