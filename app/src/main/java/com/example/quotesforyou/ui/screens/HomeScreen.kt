package com.example.quotesforyou.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quotesforyou.R
import com.example.quotesforyou.domain.model.QuoteData
import com.example.quotesforyou.ui.common.Quote
import com.example.quotesforyou.ui.viewmodel.MainViewModel
import com.example.quotesforyou.utils.Constants.emptyString

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

    Column(modifier = Modifier.fillMaxSize().padding(padding)) {
        //todo create lable style
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

        Text(
            text = stringResource(R.string.more_quotes),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp),
            color = MaterialTheme.colorScheme.secondary
        )
        LazyColumn {

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