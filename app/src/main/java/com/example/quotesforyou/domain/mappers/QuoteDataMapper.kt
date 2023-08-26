package com.example.quotesforyou.domain.mappers

import com.example.quotesforyou.data.base.AppResponse
import com.example.quotesforyou.data.base.Mapper
import com.example.quotesforyou.data.base.Resource
import com.example.quotesforyou.data.model.response.Quote
import com.example.quotesforyou.domain.model.QuoteData
import javax.inject.Inject

class QuoteDataMapper @Inject constructor() :
    Mapper<Quote, QuoteData>() {

    override fun map(input: Quote): QuoteData {
        return QuoteData(
            id = input.id,
            quoteContent = input.quoteContent,
            quoteAutor = input.author
        )
    }
}
