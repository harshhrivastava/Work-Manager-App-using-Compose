package com.example.workmanagerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.workmanagerapp.service.CustomWorker
import com.example.workmanagerapp.ui.screen.HomeScreen
import com.example.workmanagerapp.ui.theme.WorkManagerAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkManagerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val permissionState = rememberPermissionState(
                        permission = android.Manifest.permission.POST_NOTIFICATIONS
                    )

                    if (!permissionState.status.isGranted) {
                        LaunchedEffect(true) {
                            permissionState.launchPermissionRequest()
                        }
                    } else {
                        val coroutineScope = rememberCoroutineScope()
                        HomeScreen(
                            performTask = {
                                coroutineScope.launch {
                                    val workRequest = OneTimeWorkRequest.Builder(
                                        CustomWorker::class.java
                                    ).build()
                                    WorkManager
                                        .getInstance(applicationContext)
                                        .enqueue(workRequest)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}