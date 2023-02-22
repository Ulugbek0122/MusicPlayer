package uz.gita.presentation.ui.servis

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.R
import uz.gita.data.model.CommandEnum
import uz.gita.presentation.controller.MusicController
import uz.gita.presentation.controller.MusicManager
import javax.inject.Inject

@AndroidEntryPoint
class MusicService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    private val CHANNEL_ID = "DEMO"
    private var manager: NotificationManager? = null
    @Inject
    lateinit var controller:MusicController

    override fun onCreate() {
        super.onCreate()
        createChannel()
        createNotification()
    }

    private fun createNotification() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("MusicPlayer")
            .setSmallIcon(R.drawable.music_1_svgrepo_com)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(createRemoteView())
            .build()


        startForeground(1,notification)
    }

    private fun createRemoteView(): RemoteViews {
        val remoteView = RemoteViews(this.packageName, R.layout.remote_item)
        return remoteView
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(CHANNEL_ID, "MUSIC", NotificationManager.IMPORTANCE_DEFAULT)
            channel.setSound(null, null)
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager?.createNotificationChannel(channel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        controller.doneCommand(MusicManager.lastCommand,this)
        return Service.START_NOT_STICKY
    }

}