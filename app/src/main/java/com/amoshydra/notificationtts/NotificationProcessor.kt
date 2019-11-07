package com.amoshydra.notificationtts

import android.app.Notification
import android.os.Build
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationProcessor {
    fun process(sbn: StatusBarNotification): String? {
        if (!shouldProcess(sbn)) return null
        log(sbn)

        return makeSpeechText(sbn)
    }

    private fun makeSpeechText(sbn: StatusBarNotification): String? {
        return sbn.notification.extras.getString(Notification.EXTRA_TEXT)
    }

    private fun shouldProcess(sbn: StatusBarNotification): Boolean {
        val notification = sbn.notification

        if (notification.flags.and(Notification.FLAG_GROUP_SUMMARY) != 0) return false

        if (Build.VERSION.SDK_INT >= 24) {
            if (notification.extras.getString(Notification.EXTRA_TEMPLATE) != Notification.MessagingStyle::class.java.name) return false
        }

        return true
    }

    private fun log(sbn: StatusBarNotification) {
        val extras = sbn.notification.extras.keySet()
            .joinToString("\n") { key ->
                val content = sbn.notification.extras.get(key)
                var contentType = "null"
                if (content != null) {
                    contentType = content::class.java.simpleName
                }
                "$key: $contentType = $content"
            }
        Log.i("TAG", "Notification $extras")
        Log.i("TAG", "Notification \"id\": Integer = " + sbn.id)
    }
}
