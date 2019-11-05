package com.amoshydra.notificationtts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.content.ComponentName
import android.content.Context


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (hasEnabledNotificationListener(applicationContext)) {
            startService(Intent(this, NotificationListener::class.java))
        } else {
            val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            startActivity(intent)
        }
    }

    private fun hasEnabledNotificationListener(context: Context): Boolean {
        val componentName = ComponentName(context, NotificationListener::class.java)

        val enabledListenerString = Settings.Secure.getString(
            applicationContext.contentResolver,
            "enabled_notification_listeners"
        )

        return enabledListenerString?.contains(componentName.flattenToString()) ?: false
    }
}
