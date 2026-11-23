package com.hdemirbassmusic.music.utils

/** Android entry point for the shared artist wordmark lookup. */
object ArtistSignature {
    suspend fun findUrl(artistName: String): String? = com.hdemirbassmusic.music.canvas.ArtistSignature.findUrl(artistName)
}
