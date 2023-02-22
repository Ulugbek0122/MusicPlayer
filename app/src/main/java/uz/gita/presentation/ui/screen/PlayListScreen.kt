package uz.gita.presentation.ui.screen

import android.content.Intent
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.R
import uz.gita.data.model.CommandEnum
import uz.gita.databinding.ScreenPlayListBinding
import uz.gita.presentation.controller.MusicManager
import uz.gita.presentation.ui.adapters.MusicAdapter
import uz.gita.presentation.ui.servis.MusicService
import uz.gita.presentation.viewmodel.PlayListViewModel
import uz.gita.presentation.viewmodel.impl.PlayListViewModelImpl
import uz.gita.utils.myApply

@AndroidEntryPoint
class PlayListScreen:Fragment(R.layout.screen_play_list) {
    private val binding by viewBinding(ScreenPlayListBinding::bind)
    private val viewModel:PlayListViewModel by viewModels<PlayListViewModelImpl>()
    private val adapter = MusicAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {
       musicList.layoutManager = LinearLayoutManager(requireContext())
        musicList.adapter = adapter

        adapter.selectMusicPositionListener {
            MusicManager.selectMusicPos = it
            startService(CommandEnum.PLAY)
        }

        viewModel.cursorRefFlow.onEach {
            it?.let { showMusics(it) }
        }.launchIn(lifecycleScope)
    }

    private fun showMusics(cursor: Cursor){
        adapter.cursor = cursor
        adapter.notifyDataSetChanged()
    }

    private fun startService(commandEnum: CommandEnum){
        val intent = Intent(requireContext(),MusicService::class.java)
       MusicManager.lastCommand = commandEnum
        if (Build.VERSION.SDK_INT >= 26){
            requireContext().startForegroundService(intent)
        }else{
            requireContext().startService(intent)
        }
    }
}