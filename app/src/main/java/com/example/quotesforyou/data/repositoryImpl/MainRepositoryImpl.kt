package com.example.quotesforyou.data.repositoryImpl

import com.example.quotesforyou.data.base.Resource
import com.example.quotesforyou.data.model.request.GetQuotes
import com.example.quotesforyou.data.model.response.Quote
import com.example.quotesforyou.data.model.response.QuotesList
import com.example.quotesforyou.data.service.Service
import com.example.quotesforyou.domain.mappers.QuoteDataMapper
import com.example.quotesforyou.domain.model.QuoteData
import com.example.quotesforyou.domain.repository.MainRepository
import com.example.quotesforyou.utils.helpers.processData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val service: Service
) : MainRepository {
    override suspend fun getQuotes(): Flow<Resource<Quote>> {
        return processData {
            service.getRandomQuote()
        }
    }

    override suspend fun getQuotesList(inputData: GetQuotes): Flow<Resource<QuotesList>> {
        return processData {
            val map = hashMapOf<String,Any>()
            map["page"] = inputData.page
            map["limit"] = inputData.limit
            service.getQuotesList(map)
        }
    }
}