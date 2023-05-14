package com.example.rickandmorty.episode.data.list.mapper

import com.example.rickandmorty.episode.data.list.local.model.EpisodeForDetailCacheEntity
import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class EpisodeCacheEntityToEpisodeDomainMapperTest {

    private val mapper = EpisodeCacheEntityToEpisodeDomainMapper()
    private val entity = mockk<EpisodeForDetailCacheEntity>()

    @Test
    fun `mapping of input should return expected output`() {
        every { entity.id } returns 1
        every { entity.name } returns "Pilot"
        every { entity.airDate } returns "December 2, 2013"
        every { entity.episode } returns "S01E01"

        val result = mapper(entity)

        val expected = EpisodeDomain(
            1,
            "Pilot",
            "December 2, 2013",
            "S01E01"
        )
        assertEquals(expected, result)
    }
}