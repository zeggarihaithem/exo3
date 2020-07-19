package com.example.tdm2_td02_exo3.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import com.example.tdm2_td02_exo3.R


class NotificationUtils(base: Context) : ContextWrapper(base) {

    private lateinit var mManager: NotificationManager
    private val ANDROID_CHANNEL_ID: String = "com.testandroid.pushnotifications.ANDROID"
    private val ANDROID_CHANNEL_NAME: String = "ANDROID CHANNEL"


    init {
        createChannels()
    }


    private fun createChannels() {


        // create android channel
        val androidChannel = NotificationChannel(
            ANDROID_CHANNEL_ID,
            ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH
        ).also {

            // Sets whether notifications posted to this channel should display notification lights
            it.enableLights(true)
            // Sets whether notification posted to this channel should vibrate.
            it.enableVibration(true)
        }
        // Sets the notification light color for notifications posted to this channel
        androidChannel.lightColor = Color.GREEN
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        androidChannel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE



        getManager().createNotificationChannel(androidChannel)


    }

    fun getManager(): NotificationManager {

        mManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        return mManager
    }

    fun getAndroidChannelNotification(
        title: String?,
        body: String?
    ): Notification.Builder? {
        return Notification.Builder(applicationContext, ANDROID_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_android)
            .setAutoCancel(true)
    }
}