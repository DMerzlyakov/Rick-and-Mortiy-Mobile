package com.example.rickandmorty.episode.presentation.detail.mapper

import com.example.rickandmorty.episode.domain.detail.model.EpisodeDetailDomain
import com.example.rickandmorty.episode.presentation.detail.model.EpisodeDetailUi
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class EpisodeDetailDomainToEpisodeDetailUiMapperTest {

    private val mapper = EpisodeDetailDomainToEpisodeDetailUiMapper()
    private val mockEpisodeDetailDomain = mockk<EpisodeDetailDomain>()

    @Test
    fun `mapping of input should return expected output`() {
        // given
        every { mockEpisodeDetailDomain.id } returns 1
        every { mockEpisodeDetailDomain.name } returns "Pilot"
        every { mockEpisodeDetailDomain.airDate } returns "December 2, 2013"
        every { mockEpisodeDetailDomain.episode } returns "S01E01"
        every { mockEpisodeDetailDomain.characters } returns listOf(1, 2)

        // when
        val result = mapper(mockEpisodeDetailDomain)

        // then
        val expected = EpisodeDetailUi(
            id = 1,
            name = "Pilot",
            airDate = "December 2, 2013",
            episode = "S01E01",
            characters = listOf(1, 2)
            )

        assertEquals(expected, result)
    }
}