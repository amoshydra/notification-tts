package com.amoshydra.notificationtts

import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationListener : android.service.notification.NotificationListenerService() {
    companion object {
        private val TAG = NotificationListener::class.java.simpleName
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        handleListenerConnected()
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        Log.i(TAG, "Notification " + sbn.id)
        Log.i(TAG, "Notification " + sbn.notification.tickerText)

        val extras = sbn.notification.extras.keySet()
            .joinToString("\n") {
                    key -> "$key: " + sbn.notification.extras.get(key)
            }
        Log.i(TAG, "Notification $extras")
        Log.i(TAG, "Notification " + sbn.packageName)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        super.onNotificationRemoved(sbn)
        Log.i(TAG, "Removed " + sbn.id)
        Log.i(TAG, "Removed" + sbn.packageName)
    }

    private fun handleListenerConnected() {
        Log.i(TAG, "Success")
    }
}
