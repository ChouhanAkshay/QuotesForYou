package com.example.quotesforyou.data.model.response

import com.example.quotesforyou.data.base.ApiResponse
import com.google.gson.annotations.SerializedName

data class Quote (
    @SerializedName("_id")
    val id : String? = null,
    @SerializedName("content")
    val quoteContent : String? = null,
    @SerializedName("author")
    val author : String? = null
) : ApiResponse