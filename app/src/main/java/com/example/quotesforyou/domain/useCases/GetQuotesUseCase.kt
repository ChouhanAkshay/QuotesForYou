package com.example.quotesforyou.domain.useCases

import com.example.quotesforyou.data.base.BaseUseCaseUnWrappedWithInput
import com.example.quotesforyou.data.base.Resource
import com.example.quotesforyou.data.model.request.GetQuotes
import com.example.quotesforyou.data.model.response.QuotesList
import com.example.quotesforyou.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val repository: MainRepository
) : BaseUseCaseUnWrappedWithInput<GetQuotes, QuotesList>() {
    override suspend fun processRequest(inputData : GetQuotes): Flow<Resource<QuotesList>> {
        return repository.getQuotesList(inputData)
    }
}