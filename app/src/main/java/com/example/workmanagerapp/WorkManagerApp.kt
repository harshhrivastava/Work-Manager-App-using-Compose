package com.example.workmanagerapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class WorkManagerApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Notification Channel object is being created
        val notificationChannel = NotificationChannel(
            // Unique ID to identify the channel
            "GeneralWorkManager",
            // Name of the channel
            "General Notifications",
            // Importance level
            NotificationManager.IMPORTANCE_DEFAULT
        )

        // Notification Manager object is being created
        // It is used to create the notification channel
        val notificationManager = getSystemService(NotificationManager::class.java)

        // Notification channel is being created
        // using the notification manager object
        notificationManager.createNotificationChannel(notificationChannel)
    }
}