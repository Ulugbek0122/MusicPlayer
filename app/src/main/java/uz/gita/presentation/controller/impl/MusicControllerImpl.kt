package uz.gita.presentation.controller.impl

import android.content.Context
import android.database.Cursor
import android.media.MediaPlayer
import android.net.Uri
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.data.mapper.MusicMapper.getMusicDataByPosition
import uz.gita.data.model.CommandEnum
import uz.gita.domain.usecase.CursorRefUseCase
import uz.gita.presentation.controller.MusicController
import uz.gita.presentation.controller.MusicManager
import javax.inject.Inject

class MusicControllerImpl @Inject constructor(
    private val cursorRefUseCase: CursorRefUseCase
) : MusicController{
    private var _cursor : Cursor? = null
    private val cursor get() = _cursor!!
    private val scope = CoroutineScope(Dispatchers.IO + Job())
    private var _mediaPlayer : MediaPlayer? = null
    private val mediaPlayer get() = _mediaPlayer!!

    init {
        cursorRefUseCase.invoke().onEach {
            _cursor = it
        }.launchIn(scope)
    }

    override fun doneCommand(commandEnum: CommandEnum,context: Context) {
        when(MusicManager.lastCommand){
            CommandEnum.MANAGE ->{
                if (mediaPlayer == null){
                    doneCommand(CommandEnum.PLAY,context)
                    return
                }
                if (mediaPlayer.isPlaying){
                    doneCommand(CommandEnum.PAUSE,context)
                }else{
                    doneCommand(CommandEnum.PLAY,context)
                }
            }
            CommandEnum.PLAY ->{
                _mediaPlayer = MediaPlayer.create(context, Uri.parse(cursor.getMusicDataByPosition(MusicManager.selectMusicPos).data))
                mediaPlayer.setOnCompletionListener {
                    MusicManager.selectMusicPos++
                    doneCommand(CommandEnum.PLAY,context)
                }
                mediaPlayer.start()
            }
            CommandEnum.PAUSE ->{}
            CommandEnum.NEXT ->{}
            CommandEnum.PREV ->{}
            CommandEnum.CANCEL ->{}
        }
    }

    override fun onCleared() {
        scope.cancel()
        _cursor = null
        _mediaPlayer?.release()
        _mediaPlayer = null
    }
}