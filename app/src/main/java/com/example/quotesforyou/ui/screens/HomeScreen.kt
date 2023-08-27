package com.example.quotesforyou.ui.screens

import android.content.ClipData.Item
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import com.example.quotesforyou.R
import com.example.quotesforyou.domain.model.QuoteData
import com.example.quotesforyou.ui.common.Quote
import com.example.quotesforyou.ui.common.roundProgressBar
import com.example.quotesforyou.ui.viewmodel.MainViewModel
import com.example.quotesforyou.utils.Constants.emptyString
import timber.log.Timber
import kotlin.math.round

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    Scaffold(
        topBar = {
            TopBar()
        }
    ) { innerPadding ->
        Content(innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name)
            )
        },
        actions = {
            IconButton(
                onClick = {
                    //todo redirect to settings
                }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = emptyString)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    )
}

@Composable
fun Content(
    padding: PaddingValues,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val getData = mainViewModel._getData.observeAsState()
    val getQuotesList by mainViewModel._getQuotes.observeAsState()
    val loading = mainViewModel._isLoading.observeAsState()

    //provide animation effect when scroll to trending view
    val lazyListState = rememberLazyListState()
    var scrolledY = 0
    var prevOffset = 0

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(padding),
        state = lazyListState
    ) {
        //todo get trending quote once a day
        //Trending Quote
        item {
            Box(
                modifier = Modifier.fillMaxWidth()
            ){
                Column(
                    //scrolls trending data up and down based on first item position of more quotes
                    modifier = Modifier.graphicsLayer {
                        scrolledY += lazyListState.firstVisibleItemScrollOffset - prevOffset
                        translationY = scrolledY * 0.5f
                        alpha = (1 - (translationY/100))
                        Timber.e("startTransaction $translationY")
                        prevOffset = lazyListState.firstVisibleItemScrollOffset
                    }.background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text(
                        text = stringResource(R.string.trending_quote),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                    getData.value?.let { data ->
                        Quote(isLoading = false, data)
                    } ?: run {
                        Quote(
                            isLoading = true, listOfColors = listOf(
                                Color(0xFFB8B5B5),
                                Color(0xFF8F8B8B),
                                Color(0xFFB8B5B5),
                            )
                        )
                    }
                }
            }
        }

        //Label - More Quotes
        item {
            Text(
                text = stringResource(R.string.more_quotes),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp),
                color = MaterialTheme.colorScheme.secondary
            )
        }

        //List of Quotes
        if (getQuotesList.isNullOrEmpty()) {
            //load items for shimmer
            items(count = 2) {
                Quote(
                    isLoading = true, listOfColors = listOf(
                        Color(0xFFB8B5B5),
                        Color(0xFF8F8B8B),
                        Color(0xFFB8B5B5),
                    )
                )
            }
        } else {
            getQuotesList?.let { list ->
                Timber.e("startRequest : 3")
                items(list.size) { index ->
                    //check and load next items if reached the end
                    Timber.e("startRequestSize : ${list.size} $index")
                    if (index == list.size - 1
                        && (loading.value != true)
                        && mainViewModel.getHasMoreQuotes() == true
                    ) {
                        Timber.e("startRequest : 4")
                        mainViewModel.getNextPageItems()
                    }

                    Quote(isLoading = false, list[index])
                }
                item {
                    if (loading.value == true) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            roundProgressBar(
                                size = 45,
                                strokeSize = 4
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TopBarPreview() {
    TopBar()
}

@Preview
@Composable
fun ContentPreview() {
    Content(padding = PaddingValues(16.dp))
}