package uz.gita.data.model

import java.time.Duration

data class MusicData(
    val id: Int,
    val artist:String?,
    val title:String?,
    val data:String,
    val displayName:String,
    val duration: Int
)
