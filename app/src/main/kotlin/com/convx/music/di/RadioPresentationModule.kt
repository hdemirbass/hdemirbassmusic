package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.viewmodels.RadioStationViewModel
import com.hdemirbassmusic.music.viewmodels.RadioViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val radioPresentationModule = module {
    viewModelOf(::RadioStationViewModel)
    viewModelOf(::RadioViewModel)
}
