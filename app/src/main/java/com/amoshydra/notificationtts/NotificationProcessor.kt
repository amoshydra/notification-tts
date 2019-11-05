package com.amoshydra.notificationtts

import android.app.Notification
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
        if (sbn.notification.flags.and(Notification.FLAG_GROUP_SUMMARY) != 0) return false

        return true
    }
}
