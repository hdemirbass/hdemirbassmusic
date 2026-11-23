package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.viewmodels.BackupRestoreViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val backupRestorePresentationModule = module {
    viewModelOf(::BackupRestoreViewModel)
}
