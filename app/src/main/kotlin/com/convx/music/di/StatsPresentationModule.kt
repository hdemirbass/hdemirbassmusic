package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.viewmodels.StatsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val statsPresentationModule = module {
    viewModelOf(::StatsViewModel)
}
