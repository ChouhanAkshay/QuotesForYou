package com.example.quotesforyou.domain.useCases

import com.example.quotesforyou.data.base.BaseUseCaseUnWrapped
import com.example.quotesforyou.data.base.Resource
import com.example.quotesforyou.data.model.response.Quote
import com.example.quotesforyou.domain.model.QuoteData
import com.example.quotesforyou.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuoteOfTheDayUseCase @Inject constructor(
    private val repository: MainRepository
) : BaseUseCaseUnWrapped<Quote>() {
    override suspend fun processRequest(): Flow<Resource<Quote>> {
        return repository.getQuotes()
    }
}