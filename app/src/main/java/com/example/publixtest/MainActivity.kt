package com.example.publixtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.publixtest.ui.theme.PublixTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainVM: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PublixTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.fillMaxSize()
                        .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        QuoteList(vm = mainVM,
                            modifier = Modifier.weight(1f))
                        Button(
                            onClick = {
                            mainVM.fetchQuote()
                        }) {
                            Text(stringResource(R.string.fetchButton))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuoteList(vm: MainViewModel, modifier: Modifier) {
    val quotes = vm.quotes.collectAsState(emptyList())
    if (vm.loading.value) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    if (vm.error.value) {
        Dialog(
            onDismissRequest = {
            vm.error.value = false
        }) {
            Box(modifier = Modifier.background(colorResource(R.color.white))
                .padding(dimensionResource(R.dimen.dialogPadding))) {
                Text(stringResource(R.string.error))
            }
        }
    }

    LazyColumn(modifier = modifier) {
        items(quotes.value) { quote ->
            QuoteItem(quote = quote)
        }
    }
}