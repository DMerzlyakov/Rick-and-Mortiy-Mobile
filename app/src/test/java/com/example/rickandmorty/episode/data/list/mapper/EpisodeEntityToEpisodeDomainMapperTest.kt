package com.example.rickandmorty.episode.data.list.mapper

import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity
import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class EpisodeEntityToEpisodeDomainMapperTest {

    private val mockEpisodeEntity = mockk<EpisodeEntity>()
    private val mapper = EpisodeEntityToEpisodeDomainMapper()

    @Test
    fun `mapping of input should return expected output`() {
        every { mockEpisodeEntity.id } returns 1
        every { mockEpisodeEntity.name } returns "Pilot"
        every { mockEpisodeEntity.airDate } returns "2013-12-02"
        every { mockEpisodeEntity.episode } returns "S01E01"

        val result = mapper(mockEpisodeEntity)
        val expected = EpisodeDomain(1, "Pilot", "2013-12-02", "S01E01")

        assertEquals(expected, result)
    }

}