package com.example.tdm2_td02_exo3


import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.tdm2_td02_exo3.App.Companion.CHANNEL_ID
import com.example.tdm2_td02_exo3.notification.NotificationStructure
import com.example.tdm2_td02_exo3.notification.NotificationUtils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread


class MyService : Service() {

    val TAG = "MyService"

    companion object {
        lateinit var mediaPlayer: MediaPlayer
    }

    private fun showLog(message: String) {
        Log.d(TAG, message)

    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(TAG)
            .setContentText(TAG)
            .setSmallIcon(R.drawable.ic_android)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)


        thread {
            while (true) {
                val time: Date = Calendar.getInstance().time
                val timeFormat = SimpleDateFormat("HH:mm")
                val currentTime = timeFormat.format(time)
                val listHours = MainActivity.listHours
                if (currentTime in listHours) {

                    val mNotificationUtils =
                        NotificationUtils(
                            this
                        )

                    val nb: Notification.Builder? =
                        mNotificationUtils.getAndroidChannelNotification(
                            NotificationStructure.title, NotificationStructure.body
                        )
                    mNotificationUtils.getManager().notify(101, nb!!.build())


                    mediaPlayer = MediaPlayer.create(this, R.raw.azan1)
                    mediaPlayer.start()

                    Thread.sleep(61000)
                }
            }

        }



        return START_NOT_STICKY

    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}