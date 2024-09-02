package com.example.quotesforyou.widget

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontFamily
import androidx.glance.text.FontWeight.Companion.Bold
import androidx.glance.text.FontWeight.Companion.Normal
import androidx.glance.text.Text
import androidx.glance.text.TextAlign.Companion
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.quotesforyou.domain.model.QuoteData

class DailyQuoteWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            DailyQuoteWidget(QuoteData("abc","abc","abc"))
        }
    }

    @Composable
    fun DailyQuoteWidget(quote: QuoteData) {
        Box(
            modifier = GlanceModifier.padding(12.dp).fillMaxSize().background(Color.DarkGray)
        ) {
            Column(
                modifier = GlanceModifier.padding(12.dp)
            ) {
                Text(
                    text = quote.quoteContent.toString(),
                    modifier = GlanceModifier.fillMaxSize(),
                    style = TextStyle(
                        fontFamily = FontFamily("quickSand"),
                        fontWeight = Bold,
                        fontSize = 20.sp,
                        textAlign = androidx.glance.text.TextAlign.Center,
                        color = ColorProvider(Color.White)
                    )
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = quote.quoteAutor.toString(),
                    modifier = GlanceModifier.fillMaxSize(),
                    style = TextStyle(
                        fontFamily = FontFamily("quickSand"),
                        fontWeight = Normal,
                        fontSize = 16.sp,
                        textAlign = androidx.glance.text.TextAlign.End,
                        color = ColorProvider(Color.White)
                    )
                )
            }
        }
    }
}