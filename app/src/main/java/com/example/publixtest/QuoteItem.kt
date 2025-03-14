package com.example.publixtest

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun QuoteItem(quote: Quote) {
    Column {
        Text(
            text = quote.content,
            style = TextStyle(
                fontSize = dimensionResource(R.dimen.bigTextSize).value.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = quote.author,
            style = TextStyle(
                fontSize = dimensionResource(R.dimen.smallTextSize).value.sp,
                fontWeight = FontWeight.Light
            )
        )
    }
}

@Preview
@Composable
fun QuoteItemPreview() {
    val quote = Quote("This is a test", "just a test")
    QuoteItem(quote)
}