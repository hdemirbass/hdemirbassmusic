package com.hdemirbassmusic.music.search

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SearchHistorySettingsTest {
    @Test
    fun recordsNonemptyQueriesWhenHistoryIsEnabled() {
        assertTrue(shouldRecordSearchQuery("hdemirbassmusic", SearchHistorySettings()))
    }

    @Test
    fun doesNotRecordQueriesWhenHistoryIsPaused() {
        assertFalse(shouldRecordSearchQuery("hdemirbassmusic", SearchHistorySettings(paused = true)))
    }

    @Test
    fun doesNotRecordQueriesBeforeSettingsAreLoaded() {
        assertFalse(shouldRecordSearchQuery("hdemirbassmusic", null))
    }

    @Test
    fun doesNotRecordEmptyQueries() {
        assertFalse(shouldRecordSearchQuery("", SearchHistorySettings()))
    }
}
