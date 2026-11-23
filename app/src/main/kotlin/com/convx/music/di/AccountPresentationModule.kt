package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.viewmodels.AccountViewModel
import com.hdemirbassmusic.music.viewmodels.AccountSettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val accountPresentationModule = module {
    viewModelOf(::AccountViewModel)
    viewModelOf(::AccountSettingsViewModel)
}
