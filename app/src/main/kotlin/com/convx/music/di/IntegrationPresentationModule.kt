package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.viewmodels.JamViewModel
import com.hdemirbassmusic.music.viewmodels.ListenTogetherViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val integrationPresentationModule = module {
    viewModelOf(::JamViewModel)
    viewModelOf(::ListenTogetherViewModel)
}
