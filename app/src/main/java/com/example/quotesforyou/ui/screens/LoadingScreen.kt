package com.example.quotesforyou.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quotesforyou.ui.common.roundProgressBar
import com.example.quotesforyou.ui.theme.QuotesForYouTheme


@Composable
fun loadingScreen(onLoadingFinished : () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(50.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Stay positive with \n daily quotes.",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(10.dp))
            Text(
                "-Unknown",
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(Modifier.height(80.dp))

            roundProgressBar(45, 3)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    QuotesForYouTheme {
        loadingScreen(){}
    }
}