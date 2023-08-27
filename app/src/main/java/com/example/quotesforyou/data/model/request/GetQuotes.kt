package com.example.quotesforyou.data.model.request

import com.example.quotesforyou.data.base.AppResponse

data class GetQuotes(
    var page : Int = 1,
    var limit : Int = 50
) : AppResponse