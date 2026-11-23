package com.hdemirbassmusic.music.di

import android.content.Context
import androidx.media3.database.DatabaseProvider
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.NoOpCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import com.hdemirbassmusic.music.constants.MaxSongCacheSizeKey
import com.hdemirbassmusic.music.db.DatabaseDao
import com.hdemirbassmusic.music.db.InternalDatabase
import com.hdemirbassmusic.music.db.MusicDatabase
import com.hdemirbassmusic.music.eq.EqualizerService
import com.hdemirbassmusic.music.eq.data.EQProfileRepository
import com.hdemirbassmusic.music.history.HistoryLocalDataSource
import com.hdemirbassmusic.music.history.DataStoreListenHistorySettingsRepository
import com.hdemirbassmusic.music.history.ListenHistoryRepository
import com.hdemirbassmusic.music.history.ListenHistorySettingsRepository
import com.hdemirbassmusic.music.history.RoomHistoryLocalDataSource
import com.hdemirbassmusic.music.library.LocalPlaylistSongDataSource
import com.hdemirbassmusic.music.library.MusicLibraryRemoteDataSource
import com.hdemirbassmusic.music.library.MusicPlaylistRemoteDataSource
import com.music.innertube.InnertubeRepository
import com.music.innertube.YouTube
import com.music.innertube.library.InnertubeMusicLibraryDataSource
import com.music.innertube.library.InnertubeMusicPlaylistDataSource
import com.hdemirbassmusic.music.library.DataStorePlaylistDisplayPreferencesRepository
import com.hdemirbassmusic.music.library.DataStorePendingPlaylistDeletionRepository
import com.hdemirbassmusic.music.library.PendingPlaylistDeletionRepository
import com.hdemirbassmusic.music.library.PlaylistDisplayPreferencesRepository
import com.hdemirbassmusic.music.library.PlaylistRepository
import com.hdemirbassmusic.music.library.RoomPlaylistRepository
import com.hdemirbassmusic.music.search.RoomSearchHistoryRepository
import com.hdemirbassmusic.music.search.SearchHistoryRepository
import com.hdemirbassmusic.music.search.DataStoreSearchHistorySettingsRepository
import com.hdemirbassmusic.music.search.SearchHistorySettingsRepository
import com.hdemirbassmusic.music.jam.JamManager
import com.hdemirbassmusic.music.listentogether.ListenTogetherClient
import com.hdemirbassmusic.music.listentogether.ListenTogetherManager
import com.hdemirbassmusic.music.listentogether.ListenTogetherPlayerConnectionBridge
import com.hdemirbassmusic.music.lyrics.LyricsHelper
import com.hdemirbassmusic.music.lyrics.LyricsProviderRepository
import com.hdemirbassmusic.music.playback.DownloadUtil
import com.hdemirbassmusic.music.playback.MediaLibrarySessionCallback
import com.hdemirbassmusic.music.playback.PlaybackServiceConnector
import com.hdemirbassmusic.music.utils.NetworkConnectivityObserver
import com.hdemirbassmusic.music.utils.SyncUtils
import com.hdemirbassmusic.music.utils.dataStore
import com.hdemirbassmusic.music.utils.get
import com.hdemirbassmusic.music.widget.hdemirbassmusicWidgetManager
import com.hdemirbassmusic.storage.data.EncryptedNetworkServerStore
import com.hdemirbassmusic.storage.data.NetworkMusicRepository
import com.hdemirbassmusic.storage.domain.NetworkServerStore
import com.hdemirbassmusic.music.ui.screens.wrapped.WrappedAudioService
import com.hdemirbassmusic.music.ui.screens.wrapped.WrappedManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val androidAppDependenciesModule = module {
    single<CoroutineScope> { CoroutineScope(SupervisorJob() + Dispatchers.Default) }
    single<MusicDatabase> { InternalDatabase.newInstance(androidContext()) }
    single<InternalDatabase> { get<MusicDatabase>().delegate }
    single<DatabaseDao> { get<InternalDatabase>().dao }
    // Must be the signed-in global client; a new instance has no session.
    single<InnertubeRepository> { YouTube }
    single<MusicLibraryRemoteDataSource> { InnertubeMusicLibraryDataSource(get()) }
    single<MusicPlaylistRemoteDataSource> { InnertubeMusicPlaylistDataSource(get()) }
    single<DatabaseProvider> { StandaloneDatabaseProvider(androidContext()) }
    single<SimpleCache>(named("playerCache")) {
        val context: Context = androidContext()
        val cacheSize = context.dataStore[MaxSongCacheSizeKey] ?: 1024
        SimpleCache(
            context.filesDir.resolve("exoplayer"),
            if (cacheSize == -1) NoOpCacheEvictor() else LeastRecentlyUsedCacheEvictor(cacheSize * 1024 * 1024L),
            get(),
        )
    }
    single<SimpleCache>(named("downloadCache")) {
        val context: Context = androidContext()
        SimpleCache(context.filesDir.resolve("download"), NoOpCacheEvictor(), get())
    }
    single<NetworkServerStore> { EncryptedNetworkServerStore(androidContext()) }
    single { NetworkMusicRepository(get()) }
    single { NetworkConnectivityObserver(androidContext()) }
    single { RoomHistoryLocalDataSource(get()) }
    single<HistoryLocalDataSource> { get<RoomHistoryLocalDataSource>() }
    single<ListenHistoryRepository> { get<RoomHistoryLocalDataSource>() }
    single<ListenHistorySettingsRepository> {
        DataStoreListenHistorySettingsRepository(androidContext())
    }
    single<RoomPlaylistRepository> { RoomPlaylistRepository(get()) }
    single<PlaylistRepository> { get<RoomPlaylistRepository>() }
    single<LocalPlaylistSongDataSource> { get<RoomPlaylistRepository>() }
    single<PlaylistDisplayPreferencesRepository> {
        DataStorePlaylistDisplayPreferencesRepository(androidContext())
    }
    single<PendingPlaylistDeletionRepository> {
        DataStorePendingPlaylistDeletionRepository(androidContext())
    }
    single<SearchHistoryRepository> { RoomSearchHistoryRepository(get()) }
    single<SearchHistorySettingsRepository> {
        DataStoreSearchHistorySettingsRepository(androidContext())
    }
    single { ListenTogetherPlayerConnectionBridge() }
    single { ListenTogetherClient(androidContext()) }
    single { ListenTogetherManager(get(), androidContext(), get()) }
    single { JamManager(androidContext(), get(), ValueProvider { getKoin().get() }) }
    single { LyricsProviderRepository(androidContext()) }
    single { LyricsHelper(androidContext(), get(), get()) }
    single { SyncUtils(androidContext(), get(), get(), get()) }
    single { EqualizerService() }
    single { EQProfileRepository(androidContext()) }
    single { WrappedManager(get(), androidContext()) }
    single { WrappedAudioService(androidContext()) }
    single {
        DownloadUtil(
            context = androidContext(),
            database = get(),
            databaseProvider = get(),
            downloadCache = get(named("downloadCache")),
            playerCache = get(named("playerCache")),
            lyricsHelper = get(),
        )
    }
    single { MediaLibrarySessionCallback(androidContext(), get(), get()) }
    single { hdemirbassmusicWidgetManager(androidContext(), get()) }
    factory { PlaybackServiceConnector(ValueProvider { getKoin().get() }, get()) }
}
