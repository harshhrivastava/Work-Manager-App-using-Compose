package com.example.workmanagerapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.workmanagerapp.ui.theme.WorkManagerAppTheme

@Composable
fun HomeScreen(
    performTask: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            ),
    ) {
        Button(
            onClick = {
                performTask()
            }
        ) {
            Text(
                text = "Start Task",
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    WorkManagerAppTheme {
        HomeScreen(performTask = {})
    }
}