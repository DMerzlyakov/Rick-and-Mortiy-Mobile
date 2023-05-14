package com.example.rickandmorty.episode.data.detail.mapper

import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity
import com.example.rickandmorty.episode.domain.detail.model.EpisodeDetailDomain
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class EpisodeEntityToEpisodeDetailDomainTest {

    private val mockEpisodeEntity = mockk<EpisodeEntity>()
    private val mapper = EpisodeEntityToEpisodeDetailDomain()

    @Test
    fun `mapping of input should return expected output`() {
        every { mockEpisodeEntity.id } returns 1
        every { mockEpisodeEntity.name } returns "Pilot"
        every { mockEpisodeEntity.airDate } returns "December 2, 2013"
        every { mockEpisodeEntity.episode } returns "S01E01"
        every { mockEpisodeEntity.characters } returns listOf(1, 2, 3)

        val result = mapper(mockEpisodeEntity)

        val expected = EpisodeDetailDomain(
            1,
            "Pilot",
            "December 2, 2013",
            "S01E01",
            listOf(1, 2, 3)
        )
        assertEquals(expected, result)
    }

    @Test
    fun `mapping of input with empty characters should return expected output`() {
        every { mockEpisodeEntity.id } returns 1
        every { mockEpisodeEntity.name } returns "Pilot"
        every { mockEpisodeEntity.airDate } returns "December 2, 2013"
        every { mockEpisodeEntity.episode } returns "S01E01"
        every { mockEpisodeEntity.characters } returns emptyList()

        val result = mapper(mockEpisodeEntity)

        val expected = EpisodeDetailDomain(
            1,
            "Pilot",
            "December 2, 2013",
            "S01E01",
            emptyList()
        )
        assertEquals(expected, result)
    }
}