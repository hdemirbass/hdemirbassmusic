  <h1>hdemirbassmusic</h1>
  <p><strong>An open-source Android music player with a calm, glass-inspired interface.</strong></p>
  <p>
    <a href="https://github.com/hdemirbass/hdemirbassmusic/releases">
      <img src="https://img.shields.io/badge/Download-Shaly%202.0.5-5268D8?style=for-the-badge&logo=android&logoColor=white" alt="Download hdemirbassmusic shaly version 2.0.5">
    </a>
    <a href="https://github.com/hdemirbass/hdemirbassmusic/releases">
      <img src="https://img.shields.io/github/downloads/hdemirbass/hdemirbassmusic/total?style=for-the-badge&label=total%20downloads&logo=github" alt="Total hdemirbassmusic release downloads">
    </a>
  </p>
  <p>
    <img src="https://img.shields.io/github/license/hdemirbass/hdemirbassmusic?style=flat" alt="GPL-3.0 license">
    <img src="https://img.shields.io/badge/Android-8.0%2B-3DDC84?style=flat&logo=android&logoColor=white" alt="Android 8.0 and later">
  </p>
</div>

> [!WARNING]
> **The current stable version is outdated, so I have removed it from Releases.** Please try **[shaly version 2.0.5](https://github.com/hdemirbass/hdemirbassmusic/releases)** instead.

hdemirbassmusic brings music discovery, playback, lyrics, and your library together in one Android app. It is built with Kotlin, Jetpack Compose, and Media3, with custom Cupertino-style controls and Liquid Glass surfaces.

## Features

### Playback

- Browse and stream music through hdemirbassmusic's InnerTube client.
- Keep listening in the background with notification, lock-screen, and Android Auto controls.
- Save music for offline listening and manage a local library.
- Build queues and playlists, set a sleep timer, change playback speed, and use the equalizer.
- Use crossfade, Automix, and DJ-style transitions.

### Lyrics and artwork

- Follow synchronized lyrics with word-level highlighting.
- Explore animated artwork and album-inspired colors.
- Browse lyrics from multiple providers.

### Library and integrations

- Browse local music and network shares, including SMB and WebDAV.
- Import playlists from Spotify.
- Connect Discord Rich Presence, Last.fm, music recognition, and Listen Together.

### hdemirbassmusic interface

- Frosted, refractive glass surfaces for menus, sheets, and floating controls.
- Cupertino-style controls built for hdemirbassmusic's HIG and Android interaction patterns.
- Adaptive themes and layouts for phones and wider screens.

## Download

- **Stable:** Removed from Releases because it is outdated.
- **Shaly (v2.0.5):** Download **shaly version 2.0.5** from [GitHub Releases](https://github.com/hdemirbass/hdemirbassmusic/releases).

hdemirbassmusic supports Android 8.0 (API 26) and later. Install APKs only from sources you trust. Android may ask you to allow installation from the app you used to download the APK.

## Project structure

hdemirbassmusic is a Gradle multi-module Android project. Keep code in the module that owns the capability, and use its existing public APIs when another module needs it.

| Path | Responsibility |
| --- | --- |
| `app/` | Android application, screens, ViewModels, navigation, playback service, persistence, and app wiring. |
| `hig/` | Shared Cupertino-style controls, theme primitives, accessibility helpers, and glass utilities. |
| `innertube/` | InnerTube client and related models. |
| `lyrics/` | Lyrics providers, parsing, and lyrics support. |
| `canvas/` | Animated canvas and catalog features. |
| `artwork/` | Artwork loading, caching, and transitions. |
| `spotify/` | Spotify authentication and playlist import. |
| `integrations/` | Discord, Last.fm, music recognition, and related integrations. |
| `network-storage/` | SMB and WebDAV music sources. |
| `spine/` | Add-on module hosting and lifecycle. |
| `listen-together-server/` | Server component for Listen Together. |

The Android app uses Jetpack Compose for UI, Media3 for playback, Room for the local library, and DataStore for preferences. App modules are wired through the existing Hilt setup.

## Build locally

### Requirements

- JDK 21
- Android Studio with Android SDK 37 installed
- Android NDK for native components

Clone the repository, open it in Android Studio, and let Gradle sync. To build a debug APK or run unit tests from a terminal:

```bash
./gradlew :app:assembleUniversalFossDebug
./gradlew :app:testUniversalFossDebugUnitTest
