package com.amoshydra.notificationtts

import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationProcessor {
    fun process(sbn: StatusBarNotification) {
        Log.i("TAG", "Notification " + sbn.id)
        Log.i("TAG", "Notification " + sbn.notification.tickerText)

        val extras = sbn.notification.extras.keySet()
            .joinToString("\n") {
                    key -> "$key: " + sbn.notification.extras.get(key)
            }
        Log.i("TAG", "Notification $extras")
        Log.i("TAG", "Notification " + sbn.packageName)
    }
}