package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.library.libraryCoreModule
import com.hdemirbassmusic.music.search.searchHistoryModule

val appModules = listOf(
    chartsPresentationModule,
    catalogPresentationModule,
    backupRestorePresentationModule,
    accountPresentationModule,
    homePresentationModule,
    androidAppDependenciesModule,
    equalizerPresentationModule,
    networkStoragePresentationModule,
    playlistDetailPresentationModule,
    integrationPresentationModule,
    artistItemsPresentationModule,
    browsePresentationModule,
    lyricsPresentationModule,
    historyPresentationModule,
    libraryCoreModule,
    searchHistoryModule,
    localLibraryPresentationModule,
    librarySectionsPresentationModule,
    playlistPresentationModule,
    localPlaylistPresentationModule,
    searchPresentationModule,
    statsPresentationModule,
    radioPresentationModule,
    updaterDataModule,
    updaterPresentationModule,
)
