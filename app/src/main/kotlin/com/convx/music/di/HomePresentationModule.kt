package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.viewmodels.NewReleaseViewModel
import com.hdemirbassmusic.music.viewmodels.HomeViewModel
import com.hdemirbassmusic.music.history.ListenHistoryRepository
import com.hdemirbassmusic.music.history.ListenHistorySettingsRepository
import com.hdemirbassmusic.music.utils.SyncUtils
import com.hdemirbassmusic.music.ui.screens.wrapped.WrappedAudioService
import com.hdemirbassmusic.music.ui.screens.wrapped.WrappedManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homePresentationModule = module {
    viewModelOf(::NewReleaseViewModel)
    viewModel {
        val koin = getKoin()
        HomeViewModel(
            context = get(),
            database = get(),
            listenHistoryRepository = ValueProvider { koin.get<ListenHistoryRepository>() },
            listenHistorySettingsRepository = ValueProvider { koin.get<ListenHistorySettingsRepository>() },
            syncUtils = ValueProvider { koin.get<SyncUtils>() },
            wrappedManager = get<WrappedManager>(),
            wrappedAudioService = get<WrappedAudioService>(),
        )
    }
}
