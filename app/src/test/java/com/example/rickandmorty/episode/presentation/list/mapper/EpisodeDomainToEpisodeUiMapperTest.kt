package com.example.rickandmorty.episode.presentation.list.mapper

import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import com.example.rickandmorty.episode.presentation.list.model.EpisodeUi
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class EpisodeDomainToEpisodeUiMapperTest {

    private val mapper = EpisodeDomainToEpisodeUiMapper()
    private val mockEpisodeDomain = mockk<EpisodeDomain>()

    @Test
    fun `mapping of input should return expected output`() {
        // given
        every { mockEpisodeDomain.id } returns 1
        every { mockEpisodeDomain.name } returns "Pilot"
        every { mockEpisodeDomain.airDate } returns "December 2, 2013"
        every { mockEpisodeDomain.episode } returns "S01E01"

        // when
        val result = mapper(mockEpisodeDomain)

        // then
        val expected = EpisodeUi(
            id = 1,
            name = "Pilot",
            airDate = "December 2, 2013",
            episode = "S01E01",
        )

        assertEquals(expected, result)
    }
}