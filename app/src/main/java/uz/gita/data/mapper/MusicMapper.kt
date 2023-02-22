package uz.gita.data.mapper

import android.database.Cursor
import uz.gita.data.model.MusicData

object MusicMapper {
    fun Cursor.getMusicDataByPosition(pos:Int) :MusicData{
        this.moveToPosition(pos)
        return MusicData(
            this.getInt(0),
            this.getString(1),
            this.getString(2),
            this.getString(3),
            this.getString(4),
            this.getInt(5)
        )
    }
}