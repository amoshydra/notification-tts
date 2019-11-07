package com.amoshydra.notificationtts

import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationListener : android.service.notification.NotificationListenerService() {
    private val notificationProcessor = NotificationProcessor()

    companion object {
        private val TAG = NotificationListener::class.java.simpleName
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.i(TAG, "NotificationTTS Listener Service connected")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        val text = notificationProcessor.process(sbn)

        if (text == null || text.isEmpty()) return
        Log.i(TAG, text)
    }

    @Suppress("RedundantOverride")
    // onNotificationRemoved method was abstract before API 21.
    // The abstract implementation is required although redundant.
    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        super.onNotificationRemoved(sbn)
    }
}
