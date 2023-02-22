package uz.gita.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
//        if(BuilConfig.DEBUG)
//            Timber.plant(Timber.DebugTree())
    }
}