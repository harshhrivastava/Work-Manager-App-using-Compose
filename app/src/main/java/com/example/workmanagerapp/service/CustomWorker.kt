package com.example.workmanagerapp.service

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.graphics.drawable.Icon
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanagerapp.R
import kotlinx.coroutines.delay

class CustomWorker(
    private val context : Context,
    params : WorkerParameters,
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {
            delay(5000)
            customNotifications(context, "Custom Worker", "Work is done.")
            Log.d("CustomWorker", "doWork()")

            Result.success()
        } catch (e : Exception) {
            Result.failure()
        }
    }
}

fun customNotifications(context: Context, title: String, content: String) {
    val notificationManager = context.getSystemService(NotificationManager::class.java)
    val notification = Notification.Builder(context, "GeneralWorkManager")
        .setContentTitle(title)
        .setContentText(content)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setLargeIcon(Icon.createWithResource(context, R.drawable.ic_worker))
        .build()
    notificationManager.notify(1, notification)
}