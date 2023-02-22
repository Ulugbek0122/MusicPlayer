package uz.gita.presentation.ui.screen

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.R
import uz.gita.databinding.SplashScreenBinding
import uz.gita.presentation.viewmodel.SplashViewModel
import uz.gita.presentation.viewmodel.impl.SplashViewModelImpl
import uz.gita.utils.checkPermissions

@AndroidEntryPoint
class SplashScreen: Fragment(R.layout.splash_screen) {
    private val binding by viewBinding(SplashScreenBinding::bind)
    private val viewModel:SplashViewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().checkPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)){
            viewModel.getMusicFromLocal()
        }
    }
}