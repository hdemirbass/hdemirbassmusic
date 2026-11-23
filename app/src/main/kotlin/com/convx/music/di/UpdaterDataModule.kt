package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.core.updater.ApkVerifier
import com.hdemirbassmusic.music.core.updater.GitHubReleaseSource
import com.hdemirbassmusic.music.core.updater.UpdateInstaller
import com.hdemirbassmusic.music.core.updater.UpdateRepository
import com.hdemirbassmusic.music.core.updater.UpdateStore
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val updaterDataModule = module {
    factoryOf(::ApkVerifier)
    singleOf(::GitHubReleaseSource)
    singleOf(::UpdateStore)
    singleOf(::UpdateInstaller)
    singleOf(::UpdateRepository)
}
