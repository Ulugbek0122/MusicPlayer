package uz.gita.domain.repository

import android.database.Cursor

interface AppRepository {

    suspend fun getMusicFromLocal()
    fun  getCursor():Cursor
}