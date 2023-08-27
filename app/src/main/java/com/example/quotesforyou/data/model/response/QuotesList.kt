package com.example.quotesforyou.data.model.response

import com.example.quotesforyou.data.base.ApiResponse
import com.google.gson.annotations.SerializedName

data class QuotesList(
    @SerializedName("count")
    val count : Int? = null,
    @SerializedName("totalCount")
    val totalCount : Int? = null,
    @SerializedName("page")
    var page : Int? = null,
    @SerializedName("totalPages")
    var totalPages : Int? = null,
    @SerializedName("lastItemIndex")
    var lastItemIndex : Int? = null,
    @SerializedName("results")
    var results : List<Quote>? = null
) : ApiResponse