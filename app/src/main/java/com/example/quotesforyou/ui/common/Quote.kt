package com.example.quotesforyou.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.quotesforyou.domain.model.QuoteData
import com.example.quotesforyou.ui.extensions.shimmerEffect
import com.example.quotesforyou.ui.theme.QuotesForYouTheme

@Composable
fun Quote(
    isLoading: Boolean,
    quoteData: QuoteData = QuoteData(),
    listOfColors: List<Color> = listOf(),
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        Box(
            modifier = Modifier.padding(16.dp)
                .height(250.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .shimmerEffect(listOfColors)
        )
    } else {
        Box(
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = Color.White
                )
                .background(color = quoteData.quoteBackgroundColor)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = quoteData.quoteContent.toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = quoteData.quoteAutor.toString(),
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewQuote() {
    QuotesForYouTheme {
        Quote(
            isLoading = false, QuoteData(),
            listOfColors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
        )
    }
}
