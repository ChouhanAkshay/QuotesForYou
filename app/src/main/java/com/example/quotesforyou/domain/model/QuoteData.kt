package com.example.quotesforyou.domain.model

import com.example.quotesforyou.data.base.AppResponse

data class QuoteData(
    val id : String?,
    val quoteContent : String?,
    val quoteAutor : String?
) : AppResponse