package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.viewmodels.HistoryViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val historyPresentationModule = module {
    viewModelOf(::HistoryViewModel)
}
