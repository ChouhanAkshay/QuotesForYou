package com.example.quotesforyou.domain.model

import androidx.compose.ui.graphics.Color
import com.example.quotesforyou.data.base.AppResponse

data class QuoteData(
    val id : String? = null,
    val quoteContent : String? = null,
    val quoteAutor : String? = null,
    var quoteBackgroundColor : Color = Color(0xFFFFCC66),
    var quoteTextColor : Color = Color.Black
) : AppResponse