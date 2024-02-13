package com.dorbom.compose_study.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen() {

    Row(
        modifier = Modifier
            .background(Color.Black)
    ) {
        Text(text = "home")
    }
}