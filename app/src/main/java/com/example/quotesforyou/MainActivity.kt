package com.example.quotesforyou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quotesforyou.ui.navigation.Navigation
import com.example.quotesforyou.ui.theme.QuotesForYouTheme
import com.example.quotesforyou.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //todo generate splash screen
        //todo generate unit test cases
        //todo add logo
        //todo setup hilt for getting api
        //todo implement home screen
        //todo implement data load and pagination
        setContent {
            val viewModel : MainViewModel = hiltViewModel()

            QuotesForYouTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}
