package com.amoshydra.notificationtts

import android.app.Notification
import android.os.Build
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationProcessor {
    fun process(sbn: StatusBarNotification) {
        if (!shouldProcess(sbn)) return

        Log.i("TAG", "Notification " + sbn.id)
        Log.i("TAG", "Notification " + sbn.notification.tickerText)

        val extras = sbn.notification.extras.keySet()
            .joinToString("\n") {
                    key -> "$key: " + sbn.notification.extras.get(key)
            }
        Log.i("TAG", "Notification $extras")
        Log.i("TAG", "Notification " + sbn.packageName)
    }

    private fun shouldProcess(sbn: StatusBarNotification): Boolean {
        val notification = sbn.notification

        if (notification.flags.and(Notification.FLAG_GROUP_SUMMARY) != 0) return false

        if (Build.VERSION.SDK_INT >= 24) {
            if (notification.extras.getString(Notification.EXTRA_TEMPLATE) != Notification.MessagingStyle::class.java.name) return false
        }

        return true
    }
}
