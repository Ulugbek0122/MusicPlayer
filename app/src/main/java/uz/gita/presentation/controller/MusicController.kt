package uz.gita.presentation.controller

import android.content.Context
import uz.gita.data.model.CommandEnum

interface MusicController {

    fun doneCommand(commandEnum: CommandEnum,context: Context)
    fun onCleared()
}